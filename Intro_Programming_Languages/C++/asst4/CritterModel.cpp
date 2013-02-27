#include "CritterModel.h"

#define DEBUG false

// This whole set of make functions, and the use of function
// pointers below is to do a little cool stuff to make the
// code to add critters to the board a bit less redundant.
// C++ does not have very sophisticated reflection. You also
// can't make function pointers to constructors (which is
// why we have the makeX functions).

Critter *makeTiger() {
    return new Tiger();
}

Critter *makeLion() {
    return new Lion();
}

Critter *makeLemur() {
    return new Lemur();
}

Critter *makeBear() {
    return new Bear();
}

Critter *makeSunDevil() {
    return new SunDevil();
}

void CritterModel::addCritters(int numEach) {
    CritterMakerFunction makerFunctions[] = {
	&makeTiger,
	&makeLion,
	&makeLemur,
	&makeBear,
	&makeSunDevil
    };

    int numMakerFunctions = 5;

    for(int nTypes = 0; nTypes < numMakerFunctions; nTypes++) {
	CritterMakerFunction makerFunction = makerFunctions[nTypes];

	for(int i = 0; i < numEach; i++) {
	    if(!addCritter(makerFunction)) {
		return; // out of spots, all done!
	    }
	}
    }
}

bool CritterModel::addCritter(CritterMakerFunction maker) {
    unsigned int x, y;

    if(!randomOpenPoint(&x, &y)) {
	return false; // ran out of points, all done!
    }
    
    // Construct the critter by invoking the maker function
    // through the pointer.
    Critter *critter = (*maker)(); 
    grid[x][y] = critter;
    return true;    
}

class InvalidArgumentException: public std::exception
{
  virtual const char* what() const throw()
  {
    return "Invalid argument passed to function";
  }
} myInvalidArgEx;

CritterModel::CritterModel(unsigned int width, unsigned int height) {
    if(width == 0 || height == 0) {
	throw myInvalidArgEx;
    }
    
    this->width = width;
    this->height = height;
    
    // allocate some space for our 2D grid
    grid = new Critter** [width];
    for(unsigned int x = 0; x < width; x++) {
	grid[x] = new Critter* [height];
	for(unsigned int y = 0; y < height; y++) {
	    grid[x][y] = NULL;
	}
    }
    
    food = new bool* [width];
    for(unsigned int x = 0; x < width; x++) {
	food[x] = new bool[height];
	// createRandomFood will take care of initializing for us
    }

    moveCount = 0;

    createRandomFood();
    addCritters(10);
}

CritterModel::~CritterModel() {
    for(unsigned int x = 0; x < width; x++) {
	// scan the row of the board looking for critters to delete
	for(unsigned int y = 0; y < height; y++) {
	    if(grid[x][y] != NULL) {
		delete grid[x][y];
	    }
	}
	
	delete [] grid[x];
	delete [] food[x];
    }
    delete [] grid;
    delete [] food;
}

void CritterModel::createRandomFood() {
    // clear out any previous food
    for (unsigned int x = 0; x < width; x++) {
	for (unsigned int y = 0; y < height; y++) {
	    food[x][y] = false;
	}
    }

    // randomly fill some fraction of all squares
    int squaresToFill = FOOD_PERCENTAGE * width * height / 100;
    for (int i = 0; i < squaresToFill; i++) {
	int randomX = rand() % width;
	int randomY = rand() % height;
	food[randomX][randomY] = true;
    }
}

void CritterModel::printBoard() {
    for(unsigned int y = 0; y < height; y++) {
	for(unsigned int x = 0; x < width; x++) {
	    // figure out what should go here.
	    char toPrint = EMPTY;

	    if(grid[x][y] != NULL) {
		toPrint = grid[x][y]->getChar();
	    } else if(food[x][y]) {
		toPrint = FOOD;
	    }

	    std::cout << " " << toPrint;
	}
	std::cout << std::endl;
    }

}

// fill in with the random open spot.  Return false if no such spot
// could be found.
bool CritterModel::randomOpenPoint(unsigned int *x, unsigned int *y) {
    unsigned int startX = rand() % width;
    unsigned int startY = rand() % height;
    
    *x = startX;
    *y = startY;
 
    // now search for the next open spot beyond our random point
    while(grid[*x][*y] != NULL) {
	*y = *y + 1;
	
	// if wrapping y, also increment x
	if(*y == height) {  
	    *y = 0;
	    *x = (*x + 1) % width; // wrap x
	}
	
	// no spots left
	if(*x == startX && *y == startY) {
	    return false;
	}
    }

    return true;
}

// take a step in the simulation
// Move all of the critters and handle collisions.
void CritterModel::step() {
    moveCount++;

    std::cout << "\n Beginning simulation move #" << moveCount << std::endl;

    // it's not quite fair to the critters in the lower right section
    // of the map, but it's easy to track the progress, iterate
    // through the map moving each critter
    for(unsigned int x = 0; x < width; x++) {
	for(unsigned int y = 0; y < height; y++) {
	    Critter *currCritter = grid[x][y];

	    // if there's no critter here, move on
	    if(currCritter == NULL ||
	       currCritter->hasGone()) {
		continue;
	    }

	    //update the critter about some state of it's world that
	    //it may care about:
	    currCritter->setAlive(true);
	    currCritter->setX(x);
	    currCritter->setY(y);
	    currCritter->setWidth(width);
	    currCritter->setHeight(height);
	    
	    Critter *neighbor = discoverNeighbor(NORTH, x, y, NULL, NULL);
	    currCritter->setNeighbor(NORTH, neighbor == NULL ? EMPTY_NEIGHBOR : neighbor->getChar());
	    neighbor = discoverNeighbor(SOUTH, x, y, NULL, NULL);
	    currCritter->setNeighbor(SOUTH, neighbor == NULL ? EMPTY_NEIGHBOR : neighbor->getChar());
	    neighbor = discoverNeighbor(EAST, x, y, NULL, NULL);
	    currCritter->setNeighbor(EAST, neighbor == NULL ? EMPTY_NEIGHBOR : neighbor->getChar());
	    neighbor = discoverNeighbor(WEST, x, y, NULL, NULL);
	    currCritter->setNeighbor(WEST, neighbor == NULL ? EMPTY_NEIGHBOR : neighbor->getChar());

	    currCritter->beginTurn();

	    // move the critter!
	    grid[x][y] = NULL;
	    
	    Direction move = currCritter->getMove();
	    unsigned int newX, newY;
	    neighbor = discoverNeighbor(move, x, y, &newX, &newY);
	    if(neighbor == NULL) {
		grid[newX][newY] = currCritter;
		currCritter->setX(newX);
		currCritter->setY(newY);
	    } else if(currCritter->getChar() != neighbor->getChar()) {
		// two critters of different kinds, fight!

		// Note: we use the getChar method of your critters to
		// determine if your critters are "the same".  This
		// models the idea that the critter looks at the other
		// critter to determine whether it should fight.  Also
		// as a bonus, you could try to be sneaky about hiding
		// from your neighbors!

		currCritter->setX(newX);
		currCritter->setY(newY);
		
		if(DEBUG) {
		    std::cout << currCritter->getChar() << " versus " <<
			neighbor->getChar() << ": ";
		}

		Attack attack = currCritter->fight(neighbor->getChar());
		Attack defend = neighbor->fight(currCritter->getChar());

		Critter *winner;
		
		// do rock paper scissors here
		if(attack == defend) {
		    // coin flip for tie
		    winner = rand() % 2 == 0 ? currCritter : neighbor;
		} else {
		    switch(attack) {
		    case ROAR:
			// only a POUNCE defense wins (SCRATCH or FORFEIT will be a win)
			winner = defend == POUNCE ? neighbor : currCritter;
			break;
		    case POUNCE:
			// only a SCRATCH defense wins (ROAR or FORFEIT will be a win)
			winner = defend == SCRATCH ? neighbor : currCritter;
			break;
		    case SCRATCH:
			// only a ROAR defense loses (POUNCE or FORFEIT will be a win)
			winner = defend == ROAR ? neighbor : currCritter;
			break;
		    default: // FORFEIT
			// dead
			winner = neighbor;
			break;
		    }
		}

		Critter *loser = currCritter == winner ? neighbor : currCritter;

		if(DEBUG) {
		    std::cout << winner->getChar() << " wins!" << std::endl;
		}

		winner->win();
		loser->lose();

		loser->setAlive(false);
		delete loser;
		
		grid[newX][newY] = winner;
	    } else { // don't move onto your sibling!
		newX = x;
		newY = y;
		grid[newX][newY] = currCritter;
	    }

	    // make sure that currCritter isn't dead, and if not, mark it and
	    // let it know that its turn is over.
	    if(grid[newX][newY] == currCritter) {
		currCritter->setMark();
		currCritter->endTurn();
	    }
	}
    }

    for(unsigned int x = 0; x < width; x++) {
	for(unsigned int y = 0; y < height; y++) {
	    Critter *currCritter = grid[x][y];
	    
	    // if there's no critter here, move on
	    if(currCritter == NULL) {
		continue;
	    }

	    currCritter->clearMark();
	}
    }
}

Critter *CritterModel::discoverNeighbor(Direction d, unsigned int x, unsigned int y,
					unsigned int *nX, unsigned int *nY) {
    switch(d) {
    case NORTH:
	y = y - 1;
	break;
    case SOUTH:
	y = y + 1;
	break;
    case EAST:
	x = x + 1;
	break;
    case WEST:
	x = x - 1;
	break;
    }

    // handle wrapping in both negative and positive directions
    y = (y + height) % height;
    x = (x + width) % width;

    // fill in the output parameters if the caller wants the location
    if(nX != NULL) {
	*nX = x;
    }

    if(nY != NULL) {
	*nY = y;
    }

    if(grid[x][y] == NULL) {
	return NULL;
    } else {
	return grid[x][y];
    }
}
