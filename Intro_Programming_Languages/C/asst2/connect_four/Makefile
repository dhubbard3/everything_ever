connect_four: Connect4.o
	gcc -o connect_four Connect4.o

Connect4.o:  Connect4.c Connect4.h CSE240.h
	gcc -c -g -pedantic -Wall Connect4.c

clean:
	rm -f connect_four *.o core

tidy: clean
	rm -f *.*~ *~
