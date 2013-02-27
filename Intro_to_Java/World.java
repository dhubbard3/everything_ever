
import java.awt.Point;
import java.util.Random;
import javax.swing.*;

public class World{

	private Organism[][] world;
	private int xLength= 0;
	private int yLength= 0;
	private final int UP = 0;
	private final int LEFT = 1;
	private final int DOWN = 2;
	private final int RIGHT = 3;
	private int antCount = 0;
	private int dBugCount = 0;
	private int maxAnt = 0;
	private int maxDbug = 0;
	private int minAnt = 0;
	private int minDbug = 0;
	private boolean go = true;
	private Point current = new Point();
	private Point updated = new Point();
	private Random r = new Random();
	private Organism temp;
	private final int ANT = 0;
	private final int DBUG = 1;
	private DisplayFrame df = new DisplayFrame("World",20,20);
	private char[][] grid = new char[20][20];

	// Creates a two-dimensional array and fills it with
	// a set number of Ants and Doodlebugs.
	public World(int x, int y){
		world = new Organism[x][y];
		xLength = x;
		yLength = y;

		for(int i=0;i<100;i++){
			int nx = r.nextInt(xLength);
			int ny = r.nextInt(yLength);
			if(world[nx][ny]==null){
				create(nx,ny,ANT);
			}else{
				i-=1;
			}
		}
		maxAnt = antCount;
		minAnt = antCount;
		for(int i=0;i<6;i++){
			int nx = r.nextInt(xLength);
			int ny = r.nextInt(yLength);
			if(world[nx][ny]==null){
				create(nx,ny,DBUG);
			}else{
				i-=1;
			}
		}
		maxDbug = dBugCount;
		minDbug = dBugCount;
		gridDisplay(world);
		df.setGrid(grid);
	}

	// Creates a type of organism at a specified point.
	public void create(int x, int y, int type){
		if (type == ANT){
			world[x][y]=new Ant(x,y,this);
			antCount+=1;
		} else if (type == DBUG){
			world[x][y]=new Doodlebug(x,y,this);
			dBugCount+=1;
		}
	}

	// Checks an adjacent cell from a given position.
	public char adjacent(Point p, int dir){
		switch (dir){
			case UP: 	if(world[p.x-1][p.y]!=null)
							return(world[p.x-1][p.y].getType());
						break;

			case LEFT: 	if(world[p.x][p.y-1]!=null)
							return(world[p.x][p.y-1].getType());
						break;

			case DOWN: 	if(world[p.x+1][p.y]!=null)
							return(world[p.x+1][p.y].getType());
						break;

			case RIGHT: if(world[p.x][p.y+1]!=null)
							return(world[p.x][p.y+1].getType());
						break;
		}
		return '.';
	}

	// Destroys an object at a specific point.
	public void destroy(Point p){
		world[p.x][p.y]=null;
	}

	// Decrements the number of ants in the world.
	public void killAnt(){
		antCount-=1;
	}

	// Decrements the number of doodlebugs in the world.
	public void killDbug(){
		dBugCount-=1;
	}

	// Tells every organism to move, breed, and starve in that order.
	public void timeStep(int n){

		for(int q=0;q<n;q++){
			if(go == true){

				for(int i=0;i<xLength;i++){
					for(int j=0;j<yLength;j++){
						if(world[i][j]!=null){
							if(world[i][j] instanceof Ant)
								world[i][j].move();
							update();
						}
					}
				}

				for(int i=0;i<xLength;i++){
					for(int j=0;j<yLength;j++){
						if(world[i][j]!=null){
							if(world[i][j] instanceof Doodlebug)
								world[i][j].move();
							update();
						}
					}
				}

				for(int i=0;i<xLength;i++){
					for(int j=0;j<yLength;j++){
						if(world[i][j]!=null){
								world[i][j].breed();
						}
					}
				}

				for(int i=0;i<xLength;i++){
					for(int j=0;j<yLength;j++){
						if(world[i][j]!=null){
							world[i][j].starve();
						}
					}
				}
				stats();
			}
		}
		System.out.println();
		display();
		System.out.println(antCount+" ants, "+dBugCount+" doodlebugs.");
		gridDisplay(world);
		df.setGrid(grid);
	}

	// "Redraws" the world after new organism positions are obtained.
	public void update(){
		for(int i=0;i<xLength;i++){
			for(int j=0;j<yLength;j++){
				if(world[i][j]!=null){
					temp = world[i][j];
					updated = temp.getPos();
					current.x = i;
					current.y = j;
					destroy(current);
					world[updated.x][updated.y]=temp;
				}
			}
		}
	}

	// Displays the world to the console.
	public void display(){
		for(int i=0;i<xLength;i++){
			for(int j=0;j<yLength;j++){
				if(world[i][j]==null){
					System.out.print(". ");
				} else {
					System.out.print(world[i][j].getType()+" ");
				}
			}
			System.out.println();
		}
	}

	// Displays the number of ants and doodlebugs in the world.
	// Sets the Max and Min of ants and doodlebugs.
	// Terminates the simulation if either species is wiped out.
	public void stats(){

		System.out.print("\n"+antCount+" ant(s).\n");
		System.out.print(dBugCount+" doodlebug(s).\n");

		try{
			if(antCount == 0)
				throw new RuntimeException("***Ants are extinct!***");
			if(dBugCount == 0)
				throw new RuntimeException("***Doodlebugs are extinct!***");
		}catch(RuntimeException re){
			JOptionPane j = new JOptionPane();
			j.showMessageDialog(null,re.getMessage());
			System.out.print("\n"+re.getMessage()+"\n");
			go = false;
		}

		if(antCount>maxAnt)
			maxAnt = antCount;
		if(dBugCount>maxDbug)
			maxDbug = dBugCount;
		if(antCount<minAnt)
			minAnt = antCount;
		if(dBugCount<minDbug)
			minDbug = dBugCount;
	}

	// Displays the maximum and minimum of each bug type.
	public void finalStats(){
		System.out.print("\nMin: Ants - "+minAnt+" Doodlebugs - "+minDbug);
		System.out.print("\nMax: Ants - "+maxAnt+" Doodlebugs - "+maxDbug);
		System.out.println();
	}

	// Creates an array of characters for the grid display.
	public void gridDisplay(Organism[][] o){
		for(int i=0; i<xLength; i++){
			for(int j=0; j<yLength; j++){
				if(o[i][j] != null)
					grid[i][j]= o[i][j].getType();
				else
					grid[i][j]='.';
			}
		}
	}


}//end
