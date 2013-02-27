#ifndef __CONNECT4_H
#define __CONNECT4_H

#include "CSE240.h"
#include <stdio.h>
#include <stdlib.h>

#define COL_HEIGHT 6
#define NUM_COLS 7

#define WINNING_SEQUENCE_LENGTH 4

typedef enum Connect4Space {
  Empty, Maroon, Gold
} Connect4Space_t;

typedef enum Connect4Winner {
  None, Draw, MaroonWins, GoldWins
} Connect4Winner_t;

typedef struct Connect4_Column {
  int numPieces;
  Connect4Space_t pieces[COL_HEIGHT];
} Connect4_Column_t;

Bool isValidMove(int colNum);
void makeMove(int toPlace, Connect4Space_t piece);
Bool boardFull();
void clearBoard();

int  getMove(Bool isMaroonTurn);
void printBoard();

Connect4Winner_t getWinner();
void           playGame();
#endif
