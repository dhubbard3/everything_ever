
import java.util.Random;

public class Ant extends Organism{
	private int check = 0;
	private Random r = new Random();
	private int step = 0;
	private final int BREED = 3;
	private final int ANT = 0;
	private boolean canMove = true;

	public Ant(int x, int y,World w){
		super(x,y,'o',w);
	}

	// Checks random adjacent cell and proceeds to move if cell is empty.
	// Movement is then locked to prevent multiple cell jumps.
	public void move(){
		if(canMove){
			step+=1;
			check = r.nextInt(4);
			switch(check){
				case UP: 	if(checkCell(getPos(),UP)=='.'){
								if(x==0)
									x=20;
								x-=1;
							}
							break;

				case LEFT:	if(checkCell(getPos(),LEFT)=='.'){
								if(y==0)
									y=20;
								y-=1;
							}
							break;

				case DOWN:  if(checkCell(getPos(),DOWN)=='.'){
								if(x==19)
									x=-1;
								x+=1;
							}
							break;

				case RIGHT:	if(checkCell(getPos(),RIGHT)=='.'){
								if(y==19)
									y=-1;
								y+=1;
							}
							break;
			}
		}
		canMove = false;
	}

	// Ant attempts to breed during the third timestep.
	// Movement is unlocked for the next timestep.
	public void breed(){
		boolean canBreed = false;
		boolean up = false;
		boolean left = false;
		boolean down = false;
		boolean right = false;
		int tempx = this.x;
		int tempy = this.y;

		if (step==BREED){
			step = 0;
			if(checkCell(getPos(),UP)=='.'){
				canBreed=true;
				up = true;
			}
			if(checkCell(getPos(),LEFT)=='.'){
				canBreed=true;
				left = true;
			}
			if(checkCell(getPos(),DOWN)=='.'){
				canBreed=true;
				down = true;
			}
			if(checkCell(getPos(),RIGHT)=='.'){
				canBreed=true;
				right = true;
			}
		}

		while(canBreed==true){
			check = r.nextInt(4);
			switch(check){
				case UP: 	if(up){
								if(tempx==MIN)
									tempx=MAX;
								w.create(tempx-1,tempy,ANT);
								canBreed = false;
							}
							break;
				case LEFT:	if(left){
								if(tempy==MIN)
									tempy=MAX;
								w.create(tempx,tempy-1,ANT);
								canBreed = false;
							}
							break;
				case DOWN: 	if(down){
								if(tempx==MAX-1)
									tempx=MIN-1;
								w.create(tempx+1,tempy,ANT);
								canBreed = false;
							}
							break;
				case RIGHT:	if(right){
								if(tempy==MAX-1)
									tempy=MIN-1;
								w.create(tempx,tempy+1,ANT);
								canBreed = false;
							}
							break;
			}
		}
		canMove = true;
	}

	public void starve(){};

}//end