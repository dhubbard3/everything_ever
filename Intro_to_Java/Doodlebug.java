
import java.util.Random;

public class Doodlebug extends Organism{
	private int check = 0;
	private final int BREED = 8;
	private int step=0;
	private final int DBUG = 1;
	private boolean canMove = true;
	private boolean canBreed = false;
	private int full=3;
	private Random r = new Random();
	private boolean up = false;
	private boolean left = false;
	private boolean down = false;
	private boolean right = false;
	private boolean bb = false;

	public Doodlebug(int x, int y,World w){
		super(x,y,'x',w);
	}

	// Checks if a doodlebug can eat, and proceeds to move to a new random
	// position if it does not find food.
	public void move(){
		if(canMove){
			step += 1;
			if(!eat()){
				full -= 1;
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
		}
		canMove = false;
	}

	// Checks all adjacent cells for food, and proceeds to eat if found.
	public boolean eat(){
		boolean food = false;
		up = false;
		left = false;
		down = false;
		right = false;

		if(checkCell(getPos(),UP)=='o'){
			food = true;
			up = true;
			}
		if(checkCell(getPos(),LEFT)=='o'){
			food = true;
			left = true;
			}
		if(checkCell(getPos(),DOWN)=='o'){
			food =true;
			down = true;
			}
		if(checkCell(getPos(),RIGHT)=='o'){
			food = true;
			right = true;
			}

		while(food==true){
			check = r.nextInt(4);
			switch(check){
				case UP: 	if(up){
								if(x==MIN)
									x=MAX;
								x-=1;
								w.killAnt();
								full = 3;
								return true;
							}
							break;
				case LEFT:	if(left){
								if(y==MIN)
									y=MAX;
								y-=1;
								w.killAnt();
								full = 3;
								return true;
							}
							break;
				case DOWN: 	if(down){
								if(x==MAX-1)
									x=MIN-1;
								x+=1;
								w.killAnt();
								full = 3;
								return true;
							}
							break;
				case RIGHT:	if(right){
								if(y==MAX-1)
									y=MIN-1;
								y+=1;
								w.killAnt();
								full = 3;
								return true;
							}
							break;
			}
		}
		return (false);
	}


	// Determines if a doodlebug can breed at the end of a timestep.
	public void breed(){

		if(step==BREED)
			bb = true;

		canMove = true;
	}

	// Destroys doodlebugs who have not eaten in a while. Proceeds to
	// breed if it is still alive after 8 steps.
	public void starve(){
		boolean live = true;
		int tempx = this.x;
		int tempy = this.y;
		up = false;
		down = false;
		left = false;
		right = false;

		if(full==0){
			w.killDbug();
			w.destroy(getPos());
			live = false;
		}

		if (bb==true){
			step = 0;
			bb = false;
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

		if(live){
			while(canBreed==true){
				check = r.nextInt(4);
				switch(check){
					case UP: 	if(up){
									if(tempx==MIN)
										tempx=MAX;
									w.create(tempx-1,tempy,DBUG);
									canBreed = false;
								}
								break;
					case LEFT:	if(left){
									if(tempy==MIN)
										tempy=MAX;
									w.create(tempx,tempy-1,DBUG);
									canBreed = false;
								}
								break;
					case DOWN: 	if(down){
									if(tempx==MAX-1)
										tempx=MIN-1;
									w.create(tempx+1,tempy,DBUG);
									canBreed = false;
								}
								break;
					case RIGHT:	if(right){
									if(tempy==MAX-1)
										tempy=MIN-1;
									w.create(tempx,tempy+1,DBUG);
									canBreed = false;
								}
								break;
				}
			}
		}
	}

}//end