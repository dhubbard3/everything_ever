# Makefile for llist -- 09/20/2010
# This is a slightly more complex Makefile, for those who want
# to see some more sophisticated Makefile techniques.

CC		= gcc
DEBUG		= -g 
WARN            = -pedantic -Wall
CFLAGS		= $(DEBUG) $(WARN)

LL_SRC		= llist_test.c llist.c

# Saying the llist object files are the same names as the c files
LL_OBJ		= $(LL_SRC:.c=.o)

# We create the executable llist_test from the compiled
# object files.  $@ means the target name, llist_test
llist_test: $(LL_OBJ)
	$(CC) -o $@ $(CFLAGS) $(LL_OBJ) $(LIB)

clean:
	rm -f *.o llist_test core* a.out *~

# Even though make knows how to take a c file to 
# an object file given our definitions of CC and CFLAGS
# above, we still need to provide the dependencies.
llist.o:	llist.h llist.c
llist_test.o:	llist.h llist_test.c

# end of Makefile
