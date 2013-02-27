// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#ifndef SUNDEVIL_H
#define SUNDEVIL_H

#include "Critter.h"

class SunDevil : public Critter{

public:
    SunDevil(){}
    virtual ~SunDevil(){}

    virtual Attack fight(char);
    virtual Direction getMove();
    virtual char getChar();

private:
    int patrol;

};


#endif
