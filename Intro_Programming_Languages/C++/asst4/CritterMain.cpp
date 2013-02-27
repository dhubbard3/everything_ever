// CSE 240 Assignment 4 (Critters)
// Author: Kelly Wilkerson
// Adapted from University of Washington's CSE 142 Critters in Java
// by Marty Stepp and Stuart Reges
//
// Provides the main function for the simulation.
//
// YOU DON'T NEED TO EDIT THIS FILE FOR YOUR ASSIGNMENT.
//

#include <iostream>
#include "CritterModel.h"

#define BOARD_WIDTH 20
#define BOARD_HEIGHT 10

int main() {
    // seeds the random number generator
    // you can read more my typing "man srand"
    srand((unsigned)time(NULL));
    
    CritterModel model(BOARD_WIDTH, BOARD_HEIGHT);

    while(true) {
	char buffer[256];
	model.printBoard();
	
	std::cout << "Press enter to take another step in the simulation, or type q to quit> ";
	
	std::cin.getline(buffer, 256);

	if(buffer[0] == 'q' || buffer[0] == 'Q') {
	    break;
	}
        
	model.step();
    }
    
    return 0;
}

