#include "Critter.h"

Direction randomDirection() {
    return (Direction)(rand() % NUM_DIRECTIONS);
}

Critter::Critter() {
    alive = true;
    mark = false;
    neighbors = new char[5];
    for(int i = 0; i <  5; i++) {
	neighbors[i] = EMPTY_NEIGHBOR;
    }
}

Critter::~Critter() {
    delete [] neighbors;
}


char Critter::getChar() {
    return '?';
}


// Returns the height of the game simulation world.
unsigned int Critter::getHeight() {
    return height;
}

// Returns the animal that is 1 square in the given direction away
// from this animal.  A blank space, " ", signifies an empty square.
char Critter::getNeighbor(Direction direction) {
    return neighbors[direction];
}

// Returns the width of the game simulation world.
unsigned int Critter::getWidth() {
    return width;
}

// Returns this animal's current x-coordinate.
unsigned int Critter::getX() {
    return x;
}

// Returns this animal's current y-coordinate.
unsigned int Critter::getY() {
    return y;
}

// Returns true if this animal is currently alive.
// This will return false if this animal has lost a fight and died.
bool Critter::isAlive() {
    return alive;
}

// Sets whether or not this animal is currently alive.
// This method is called by the simulator and not by your animal itself.
void Critter::setAlive(bool alive) {
    this->alive = alive;
}

// Sets the height of the game simulation world to be the given value,
// so that future calls to getHeight will return this value.
// This method is called by the simulator and not by your animal itself.
void Critter::setHeight(unsigned int height) {
    this->height = height;
}

// Sets the neighbor of this animal in the given direction to be the given value,
// so that future calls to getNeighbor in that direction will return this value.
// This method is called by the simulator and not by your animal itself.
void Critter::setNeighbor(Direction direction, char value) {
    neighbors[direction] = value;
}

// Sets the width of the game simulation world to be the given value.
// so that future calls to getWidth will return this value.
// This method is called by the simulator and not by your animal itself.
void Critter::setWidth(unsigned int width) {
    this->width = width;
}

// Sets this animal's memory of its x-coordinate to be the given value.
// so that future calls to getX will return this value.
// This method is called by the simulator and not by your animal itself.
void Critter::setX(unsigned int x) {
    this->x = x;
}

// Sets this animal's memory of its y-coordinate to be the given value.
// so that future calls to getY will return this value.
// This method is called by the simulator and not by your animal itself.
void Critter::setY(unsigned int y) {
    this->y = y;
}

const char *fightString(Attack a) {
    switch(a) {
    case ROAR:
	return "roar";
    case POUNCE:
	return "pounce";
    case SCRATCH:
	return "scratch";
    default: // FORFEIT
	return "forfeit";
    }
}

const char *directionString(Direction d) {
    switch(d) {
    case NORTH:
	return "north";
    case SOUTH:
	return "south";
    case EAST:
	return "east";
    default: // WEST
	return "west";
    }
}

// used by your simple testing program to verify the objects
// behave as described
void Critter::printBehavior() {
    std::cout << "\t          symbol: " << getChar() << std::endl;
    std::cout << "\tsample movements: ";
    for(int i = 0; i < 6; i++) {
	std::cout << directionString(getMove()) << " ";
    }
    std::cout << std::endl;

    std::cout << "\t    fighting a !: " << fightString(fight('!')) << std::endl;
    std::cout << "\t    fighting a L: " << fightString(fight('L')) << std::endl;
    std::cout << "\t    fighting a T: " << fightString(fight('T')) << std::endl;
    std::cout << "\t    fighting a B: " << fightString(fight('B')) << std::endl;
    std::cout << "\t    fighting a D: " << fightString(fight('D')) << std::endl;
}

