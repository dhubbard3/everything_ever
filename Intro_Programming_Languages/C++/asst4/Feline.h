// CSE 240 Assignment 4 (Critters)
// Name: Dave Hubbard

#ifndef FELINE_H
#define FELINE_H

#include "Critter.h"


class Feline : public Critter{

public:
    Feline() {}
    virtual ~Feline(){}

    virtual Attack fight (char);
};

#endif

