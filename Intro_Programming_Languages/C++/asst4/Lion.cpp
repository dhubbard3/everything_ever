// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#include "Lion.h"


Attack Lion::fight(char opponent){
    if(opponent == 'B' || opponent == 'T'){
	return ROAR;
    }else{
	return POUNCE;
    }
}

Direction Lion::getMove(){
    int i = rand() % 4;
    if(i < 2){
	return NORTH;
    }else{
	return SOUTH;
    }
}

char Lion::getChar(){
    return 'L';
}
