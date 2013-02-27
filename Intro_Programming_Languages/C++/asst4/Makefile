# Makefile for critters -- 9/30/2010
# This makefile is more complex, to better support the additional files
# you'll be adding with more critters.

CC = g++
DEBUG = -g
WARN = -pedantic -Wall -ansi
STUFLAGS = $(DEBUG) $(WARN)

CRITTER_H = Critter.h Lemur.h Bear.h Tiger.h Lion.h SunDevil.h
CRITTER_SRC = Critter.cpp CritterTest.cpp Lemur.cpp Feline.cpp	\
Tiger.cpp Lion.cpp Bear.cpp SunDevil.cpp

SIM_H   = $(CRITTER_H) CritterModel.h
SIM_SRC = Critter.cpp Lemur.cpp Feline.cpp Tiger.cpp Lion.cpp Bear.cpp	\
SunDevil.cpp CritterModel.cpp CritterMain.cpp

critter_test: $(CRITTER_SRC) $(CRITTER_H)
	$(CC) $(STUFLAGS) -o $@ $(CRITTER_SRC)

critters: $(SIM_SRC) $(SIM_H)
	$(CC) $(STUFLAGS) -o $@ $(SIM_SRC)

# makes regular critters from Prof. Wilkerson's code, without the
# -g flag that saves symbols for debugging
sol_critter_test: $(CRITTER_SRC) $(CRITTER_H)
	$(CC) $(WARN) -o $@ $(CRITTER_SRC)

sol_critters: $(SIM_SRC) $(SIM_H)
	$(CC) $(WARN) -o $@ $(SIM_SRC)

clean:
	rm -f *.o *~ critters \#* critter_test

macClean: clean
	rm -Rf *.dSYM
