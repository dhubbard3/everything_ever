// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#ifndef LION_H
#define LION_H

#include "Feline.h"


class Lion : public Feline{

public:
    Lion(){};
    virtual ~Lion(){};

    virtual Attack fight(char);
    virtual Direction getMove();
    virtual char getChar();

};

#endif
