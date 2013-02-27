// CSE 240 Assignment 4 (Critters)
// Author: Kelly Wilkerson
// Adapted from University of Washington's CSE 142 Critters in Java
// by Marty Stepp and Stuart Reges

// This class models all of the critters in the simulated arena.

#ifndef CRITTERMODEL_H
#define CRITTERMODEL_H

#include <exception>
#include <cstdlib>   // for srand and rand
#include <iostream>

#include "Critter.h"
#include "Tiger.h"
#include "Lion.h"
#include "Lemur.h"
#include "Bear.h"
#include "SunDevil.h"

// how to display the empty squares
#define EMPTY '.'

// EMPTY_NEIGHBOR in Critter.h is how to represent an empty critter


#define DEFAULT_CRITTER_COUNT 25

#define STARTING_HUNGER 4

// Food constants
#define FOOD_PERCENTAGE 5
#define FOOD_RESPAWN_INTERVAL 50
#define FOOD '.'

// giving my function pointer a less-scary type name.
typedef Critter *(*CritterMakerFunction)();

class CritterModel {
private:
    unsigned int height; // height of the arena
    unsigned int width;  // width of the arena
    Critter *** grid;  // 2D array of critters in arena
                       // each element is a pointer to a critter
    bool ** food;     // 2D array of food locations
    int moveCount;
    
public:
    // constructs a model of the given dimensions
    CritterModel(unsigned int width, unsigned int height);
    
    // Destructor
    virtual ~CritterModel();

    // prints the state of the board
    void printBoard();

    // take a step in the simulation
    void step();


private:
    void createRandomFood();
    void addCritters(int numEach);
    bool addCritter(CritterMakerFunction maker);

    // fill in with the random open spot
    bool randomOpenPoint(unsigned int *x, unsigned int *y);

    // find a neighbor in a given direction, and optionally get its coords
    Critter *discoverNeighbor(Direction d, unsigned int x, unsigned int y,
			      unsigned int *nX, unsigned int *nY);

    bool addTiger();
    bool addLion();
};

#endif
