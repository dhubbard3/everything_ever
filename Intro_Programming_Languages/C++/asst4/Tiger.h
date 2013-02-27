// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#ifndef TIGER_H
#define TIGER_H

#include "Feline.h"

// Write your class declaration for your Tiger class here.
class Tiger : public Feline{

public:
    Tiger() {}
    virtual ~Tiger(){}

    
    virtual Direction getMove();
    virtual char getChar();

private:
    int moveTurn;
    Direction currentDir;
};

#endif
