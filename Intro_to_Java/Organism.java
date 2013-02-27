
import java.awt.Point;

public abstract class Organism{

	protected int x = 0;
	protected int y = 0;
	protected World w;
	private Point p = new Point();
	private char bug;
	final int UP = 0;
	final int LEFT = 1;
	final int DOWN = 2;
	final int RIGHT = 3;
	final int MIN = 0;
	final int MAX = 20;

	public Organism(int nx, int ny, char type, World w){
		x = nx;
		y = ny;
		bug = type;
		this.w = w;
	}

	// Creates a point from the current position of the Organism.
	public Point getPos(){
		p.x = x;
		p.y = y;
		return p;
	}

	// Returns the character symbol for the Organism to determine type.
	public char getType(){
		return bug;
	}

	// Checks adjacent cells to the current position for various reasons.
	public char checkCell(Point pos, int dir){
		Point temp = pos;

		switch(dir){
			case UP:	if(temp.x==MIN)
							temp.x=MAX;
						break;

			case LEFT:	if(temp.y==MIN)
							temp.y=MAX;
						break;

			case DOWN:	if(temp.x==MAX-1)
							temp.x=MIN-1;
						break;

			case RIGHT:	if(temp.y==MAX-1)
							temp.y=MIN-1;
						break;
		}
		return(w.adjacent(temp,dir));
	}


	public abstract void move();
	public abstract void breed();
	public abstract void starve();

}//end
