// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#include "Tiger.h"



int moveTurn = 1;
Direction currentDir = (Direction) (rand() % 4);

Direction Tiger::getMove(){
    if(moveTurn >= 3){
	currentDir = (Direction) (rand() % 4);
	moveTurn = 0;
    }
    moveTurn++;
    return currentDir;
}

char Tiger::getChar(){
    return 'T';
}
    
