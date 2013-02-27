/*
 * rotate.c
 *
 * This program is supposed to print get an input string
 * from the user, then print it rotated on the line.
 * Example:
 * Enter a string for me to rotate: I love CSE240!!!
 * I Love CSE240!!!
 *  Love CSE240!!!I
 * Love CSE240!!!I 
 * ove CSE240!!!I L
 * ve CSE240!!!I Lo
 * e CSE240!!!I Lov
 *  CSE240!!!I Love
 * CSE240!!!I Love 
 * SE240!!!I Love C
 * E240!!!I Love CS
 * 240!!!I Love CSE
 * 40!!!I Love CSE2
 * 0!!!I Love CSE24
 * !!!I Love CSE240
 * !!I Love CSE240!
 * !I Love CSE240!!
 * I Love CSE240!!!
 *
 * WARNING: This code contains several BUGS!
 */

#include <stdio.h>
#include <string.h>

int main()
{
	int len;
	int lineno;
	int i = 0;
	char input[256];

	printf("Enter a string for me to rotate: ");

	/* This scanf format string specifies that scanf should read a
	   string of max length 255.  It also specifies that scanf
	   should consider any characters that are not tab or newline
	   as part of the string (that way we can have spaces). 

	   There is no bug on this line, even though this format
	   string may seem intimidating at first.
	   
	   Something to ask yourself: why is the max length 255 and
	   not 256? */
	scanf("%[^\t\n]255s", input);
	len = strlen(input);

	/*Changed all %s to %c. Initialized i in 2nd for loop to 0.*/
	for (lineno = 0; lineno < len; lineno = lineno + 1) {
		i = lineno;
		while (input[i] != '\0') {
		    printf("%c", input[i]);
			i = i + 1;
		}
		
		for (i = 0 ; i < lineno; i = i+1) {
		    printf("%c", input[i]);
		}

		putchar('\n');
	}
	printf("%s\n", input);

	return 0;
}
/* The printf statements were set up to receive strings instead of characters.
   Variable i in the second for loop was set to 1, thus cutting off the first
   letter in the string for all but the first and last lines.*/
