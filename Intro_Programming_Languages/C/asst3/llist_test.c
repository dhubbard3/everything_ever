/* llist_test.c -- harness for testing the llist library functions
 *
 * Authors:
 *   Created by Penny Ellard 10/30/93
 *   Recrafted by Mike Smith 10/18/2003
 *   Adapted for ASU's CSE240 09/21/2010
 */ 

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>

#include "llist.h"

#define	MAX_LISTS	8

/* Declarations of the student lists */
LList_t	*sLists[MAX_LISTS];

/* Command codes */
typedef	enum	{
    SHOW_CODE	= 's',
    EMPTY_CODE	= 'e',
    APPEND_CODE	= 'a',
    INSERT_CODE	= 'i',
    LOOKUP_CODE	= 'l',
    REMOVE_CODE	= 'r',
    DELETE_CODE = 'd',
    HEAD_CODE	= 'h',
    TAIL_CODE	= 't',
    NEXT_CODE	= 'n',
    PREV_CODE	= 'p',
    HELP_CODE	= '?',
    QUIT_CODE	= 'q',
    NO_CODE	=  0
} cmd_t;

void print_instructions(void);
int  read_command(cmd_t *cmd, int *list_index, int *value);
void do_command(cmd_t cmd, int list_index, int value, int argc);
void list_modify(cmd_t cmd, int index, int value);
void list_move_ptr(cmd_t cmd, int index);
void init_lists(void);
void show_list(int list_index);
void is_empty_list(int list_index);
Bool bad_usage(int num_args, int correct_num_args, int list_index);

/*
 * main -- A simple program to test the llist functions.
 */
int
main()
{

    printf("Welcome to the CSE240 llist test program!\n");

    print_instructions();

    init_lists();

    for (;;) {
	cmd_t	cmd_code;
	int	value;
	int	list_index;
	int	arg_count;

	arg_count = read_command(&cmd_code, &list_index, &value);

	do_command(cmd_code, list_index, value, arg_count);
    }

    return 0;
}

/*
 * print_instructions -- Display some instructions for this program.
 */
void
print_instructions(void)
{
    printf( "\nEach command has the following form:   C  N  V\n");
    printf ("\tC is a single character representing the command.\n");
    printf ("\tN is an integer between 0 and %d indicating the list to use.\n",
	    MAX_LISTS - 1);
    printf ("\tV is the value to use as an argument to the command.\n");
    printf ("\tFor some of the commands, N and/or V are not required.\n" );
    printf ("\n");
    printf ("Commands:\n");
    printf ("\ts N   -- show list N.\n");
    printf ("\te N   -- state whether list N is empty.\n");
    printf ("\ta N V -- insert V into list N after current element.\n");
    printf ("\ti N V -- insert V into list N before current element.\n");
    printf ("\tl N V -- look up item V in list N starting at the "
	    "current element.\n");
    printf ("\tr N V -- remove first V from list N starting at the "
	    "current element.\n");
    printf ("\td N   -- delete the entire list N.\n");
    printf ("\th N   -- move to the head of list N.\n");
    printf ("\tt N   -- move to the tail of list N.\n");
    printf ("\tn N   -- move to the next element of list N.\n");
    printf ("\tp N   -- move to the previous element of list N.\n");
    printf ("\t?     -- display the instructions.\n");
    printf ("\tq     -- quit the program.\n");
    printf ("\n");
    printf ("To debug your llist functions, we recommend opening a\n"
	    "second terminal window, and running sol_llist_test in that\n"
	    "window, entering the same commands.  That way, you can compare\n"
	    "your linked lists to the solution.\n");
    printf("\n");
}

/*
 * read_command --
 *
 * Read in a command line from the user, and pull it apart into the C
 * (the command), N (the list index), and V (the value).
 *
 * Return the number of arguments read.
 */
int
read_command(cmd_t *C, int *N, int *V)
{
    char	command[80];
    int	rc;
    char	cmd_char;

    printf("=> ");
    fgets(command, 80, stdin);

    /* Check for end-of-input on file redirection during automated testing. */
    if (strlen(command) == 0) {
	printf("Goodbye.\n");
	exit(0);
    }

    /*
     * This call to sscanf is a little fragile, and may be
     * confused by bad input.
     */
    rc = sscanf(command, "%c %d %d", &cmd_char, N, V);

    *C = cmd_char;

    return rc;
}

/*
 * do_command -- Carry out the given command.
 */
void
do_command(cmd_t cmd, int list_index, int value, int argc)
{
    LList_t *student_prev = NULL;
    
    switch (cmd) {
      case APPEND_CODE:
      case INSERT_CODE:
      case LOOKUP_CODE:
      case REMOVE_CODE:
	if (bad_usage(argc, 3, list_index))
	    break;

	printf("cmd = %c, list_index = %d, value = %d\n",
	       cmd, list_index, value);

	list_modify(cmd, list_index, value);
	show_list(list_index);
	break;

      case DELETE_CODE:
	if (bad_usage(argc, 2, list_index))
	    break;

	printf("cmd = %c, list_index = %d\n", cmd, list_index);

        if (sLists[list_index] != NULL) {
            student_prev = sLists[list_index]->prev;
        }
    
        llist_delete_list(sLists[list_index]);
        sLists[list_index] = student_prev;

	show_list(list_index);
	break;

      case NEXT_CODE:
      case PREV_CODE:
      case HEAD_CODE:
      case TAIL_CODE:
        if (bad_usage (argc, 2, list_index))
	    break;

        printf("cmd = %c, list_index = %d\n", cmd, list_index);

        list_move_ptr(cmd, list_index);
        show_list(list_index);
        break;

      case SHOW_CODE:
        if (bad_usage(argc, 2, list_index))
	    break;

        show_list(list_index);
        break;

      case EMPTY_CODE:
        if (bad_usage(argc, 2, list_index))
	    break;

        is_empty_list(list_index);
        break;

      case HELP_CODE:
        print_instructions();
        break;

      case QUIT_CODE:
        printf("Goodbye.\n");
        exit(0);  /* Ok since this routine acts like main in this program */

      default:
        printf("Unrecognized command.\n");
        printf("The '?' command provides help.\n");
        break;
    }
}

/*
 * list_modify --
 *
 * Modify a list in some way:
 *
 * Given a command code, the index of the list, and the value, insert,
 * lookup, or remove the appropriate element in the list.
 *
 */
void
list_modify(cmd_t cmd, int i, int value)
{
    LList_t *rc, *prev;  /* pointer variables for tricky functions */

    switch (cmd) {

      case APPEND_CODE:
        sLists[i] = llist_insert_after(sLists[i], value);
        break;

      case INSERT_CODE:
        sLists[i] = llist_insert_before(sLists[i], value);
        break;

      case LOOKUP_CODE:
	rc = llist_lookup(sLists[i], value);
	if (rc == NULL) {
	    printf("Value not in list %d.\n", i);
	} else {
	    sLists[i] = rc;
	}

	break;

      case REMOVE_CODE:
        if (sLists[i] == llist_head(sLists[i])) {
	    /* nothing special to do if remove invoked on head of list */
	    sLists[i] = llist_remove(sLists[i], value);
	} else {
	    /* tricky case: remove from sublist */
	    prev = sLists[i]->prev;
	    rc = llist_remove(sLists[i], value);
	    sLists[i] = (rc == NULL) ? prev : rc;
	}

        break;

      default:
        assert(FALSE);  /* unexpected and therefore unhandled command code */
        break;
    }
}

/*
 * list_move --
 *
 * Modify which element of the list is pointed to.
 *
 * Given a command code, and the index of the list, move to the next,
 * previous, first, or last element of this list.
 *
 */
void
list_move_ptr(cmd_t cmd, int i)
{
    if (sLists[i] != NULL) {
        switch (cmd) {
	  case NEXT_CODE:
	    if (sLists[i]->next != NULL) {
		sLists[i] = sLists[i]->next;
	    } else {
		printf("At end of Student List %d.\n", i);
	    }
	    break;

	  case PREV_CODE:
	    if (sLists[i]->prev != NULL) {
		sLists[i] = sLists[i]->prev;
	    } else {
		printf("At start of Student List %d.\n", i);
	    }
	    break;

	  case HEAD_CODE:
	    sLists[i] = llist_head(sLists[i]);
	    break;

	  case TAIL_CODE:
	    sLists[i] = llist_tail(sLists[i]);
	    break;

	  default:
            assert(FALSE);  /* unexpected and unhandled command code */
	    break;
	}
    } else {
	printf("Student List %d is empty; nowhere to move.\n", i);
    }
}

/*
 * init_lists --
 *
 * Set all the list pointers to NULL.
 *
 */
void
init_lists(void)
{
    int	i;

    for (i = 0; i < MAX_LISTS; i++) {
	sLists[i] = NULL;
    }
}

/*
 * show_list --
 *
 * display the sLists[list_index] and cLists[list_index] lists on the
 * screen.
 *
 */
void
show_list(int list_index)
{
    LList_t *curr = sLists[list_index];

    printf("Student List [%d] =  ", list_index);

    /* first, check to see if the list is empty */
    if(curr == NULL) {
      printf("empty\n");
      return;
    }

    /* Otherwise, next we need to rewind to the beginning of the list.*/
    curr = llist_head(curr);


    /* Now, print the list, making sure to bracket the spot in the
       list that the cursor is currently at.
    */
    while(curr !=NULL) {
      /* if the cursor is pointing to this element */
      if(curr == sLists[list_index]) {
	printf("{%d} ", curr->value);
      } else {
	printf("%d ", curr->value);
      }
      curr = curr->next;
    }
    printf("\n");
}

/*
 * is_empty_list -- check the empty status of the sLists[list_index].
 */
void
is_empty_list(int list_index)
{
    if (sLists[list_index] == NULL) {
	printf ("The Student List [%d] is empty.\n", list_index);
    } else {
	printf ("The Student List [%d] is not empty.\n", list_index);
    }
}

/*
 * bad_usage --
 * 
 * Return 1 if the user has specified the wrong number of arguments,
 * or if list_index is too large.  Returns 0 otherwise.
 *
 */
Bool
bad_usage(int num_args, int correct_num_args, int list_index)
{
    if (num_args != correct_num_args) {
	printf("Command requires a different number of arguments.\n");
	return TRUE;
    }

    if ((list_index < 0) || (list_index >= MAX_LISTS)) {
	printf("Illegal list index (%d).\n", list_index);
	return TRUE;
    }

    return FALSE;
}

/*
 * end of llist_test.c
 */
