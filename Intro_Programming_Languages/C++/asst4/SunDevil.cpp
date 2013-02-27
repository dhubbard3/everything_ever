// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#include "SunDevil.h"

int patrol = 0;

//Attacks are randomly chosen 'cause that's how Sun Devils roll.
Attack SunDevil::fight(char meh){
    return (Attack) (rand() % 4);
}

//Sun Devils patrol the area they are in.
Direction SunDevil::getMove(){
    switch(patrol){
    case 0: patrol = 2;
	return NORTH;
	break;
    case 1: patrol = 3;
	return SOUTH;
	break;
    case 2: patrol = 1;
	return EAST;
	break;
    case 3: patrol = 0;
	return WEST;
    default: return (Direction)(rand()%4);
    }
}

char SunDevil::getChar(){
    return 'D';
}
