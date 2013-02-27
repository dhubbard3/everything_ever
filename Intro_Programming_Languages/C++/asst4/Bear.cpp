// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#include "Bear.h"

// Write your definition for the methods in your Bear class here,
// except for any methods that you wrote inlined into the class
// declaration.
Direction  Bear::getMove(){
    int i;
    i = rand() % 4;
	if(i<=1){
	    return (Direction) 0;
	}else{
	    return (Direction) 3;
	}
}

Attack Bear::fight(char opponent){
    return SCRATCH;
}

char Bear::getChar(){
    return 'B';
}
