#include "Lemur.h"

// Definitions for the methods of our Lemur class.

// Lemurs move randomly.
Direction Lemur::getMove() {
    return randomDirection();
}

// Lemurs always lose a fight.
Attack Lemur::fight(char notUsed) {
    return FORFEIT;
}

// Lemurs look like exclamation points in our simulator
// because they are scared!
char Lemur::getChar() {
    return '!';
}
