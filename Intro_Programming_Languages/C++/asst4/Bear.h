// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#ifndef BEAR_H
#define BEAR_H

#include "Critter.h"

class Bear : public Critter{

public:
    Bear() {}
    virtual ~Bear(){}

    virtual Attack fight (char);
    virtual Direction getMove();
    virtual char getChar();
};


// Don't forget the semicolon at the end of your class declaration!
#endif
