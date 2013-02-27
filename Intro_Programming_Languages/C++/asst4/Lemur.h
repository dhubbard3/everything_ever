#ifndef LEMUR_H
#define LEMUR_H

#include "Critter.h"

// Class declaration for the Lemur class.

class Lemur : public Critter {
public:
    //We've written the constructor and destructor for our
    //Lemur class inlined right into the class declaration
    //because they are VERY simple.
    Lemur() {}
    virtual ~Lemur() {}

    //We've just written the method declarations in the 
    //class declaration.  The method definitions will
    //be in Lemur.cpp.
    virtual Direction getMove();
    virtual Attack fight(char);
    virtual char getChar();
};

#endif
