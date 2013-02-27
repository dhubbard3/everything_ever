/* protect against multiple includes of the definitions in this h file */
#ifndef __LLIST_H
#define __LLIST_H

/* CSE240 Assignment 3 - Linked Lists
 * Name: Dave Hubbard
 * Email: dehubbar@asu.edu
 * llist.h -- student library for doubly-linked lists
 */

/* useful boolean definition */
typedef enum { FALSE, TRUE } Bool;

/* My structure definition for a doubly-linked list node. */

/* YOUR CODE WON'T COMPILE UNTIL YOU FILL THIS IN :) */


typedef struct LList{

    struct LList *next;
    struct LList *prev;
    int value;

} LList_t;


/* end of structure definition */


/*
 * My functions that operate on linked lists:
 * (You implement these in llist.c, these are just function 
 * declarations.)
 */

LList_t *llist_create(int val);
void     llist_delete(LList_t *ele);
LList_t *llist_head(LList_t *ele);
LList_t *llist_tail(LList_t *ele);
LList_t *llist_lookup(LList_t *list, int val);
LList_t *llist_insert_before(LList_t *ele, int val);
LList_t *llist_insert_after(LList_t *ele, int val);
LList_t *llist_remove(LList_t *list, int val);
void     llist_delete_list(LList_t *list);

/*
 * end of llist.h
 *
 * Author list:
 *   created by Dan Ellard for Harvard's CS50 10/30/96
 *   amended by Penny Ellard 11/7/99
 *   recrafted by Mike Smith 10/18/2003
 *   adapted for ASU's CSE240 by Kelly Wilkerson 09/21/2010
 *   edited by Kelly Wilkerson 02/10/2011
 */

#endif
