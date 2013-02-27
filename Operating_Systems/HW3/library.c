/******************************************************************** 

library.c
Authors: Daniel Heath, Dave Hubbard
Contains code snippet from Everett F. Carter Jr. located at
http://www.taygeta.com/random/boxmuller.html and uses techniques
from [1]"The Little Book of Semaphores" by Allen B. Downey.

********************************************************************/


#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <pthread.h>
#include <math.h>
#include <time.h>
#include <sys/time.h>
#include <unistd.h>
#include "library.h"

#define N 3
#define M 13
#define T 2000
#define SLEEP 2

int DEBUG_MODE = TRUE; /*Shows secondary debug messages*/

book_t bookshelf[M];
book_t no_book;			/*Represents no book possesion*/
pthread_mutex_t mutex;
sem_t lib_sem;
sem_t share_1;
sem_t share_2;
int book_count = M;		/*Current number of unpicked books*/
int book_search = M; 	/*Current range for selectable books*/
int student_count = 0; 	/*Current number of students in the library*/
int share_count = 0; 	/*Number of students in share_impressions()*/
int share_pages = 0;	/*Used for share_impressions() share time*/
int finished = FALSE;	/*Checks for empty bookshelf*/


void* student_thread(void* p)
{
	book_t book;
	int done;
	meet_at_the_library();
	do {
		book = pick_a_book();
		read_book(book);
		done = share_impressions(book);
		return_book(book);
	} while (done == FALSE);
	pthread_exit(NULL);
}

/*Uses rendezvous technique.[1]*/
void meet_at_the_library(){
	print_stamp();
	printf("Student%ld has arrived.\n", pthread_self());
	pthread_mutex_lock(&mutex); 		/***start***/
	student_count += 1;               	 
	if (student_count == N){           	  	
		sem_post(&lib_sem);	
		}	  	  
	pthread_mutex_unlock(&mutex);		/****end****/
	
	sem_wait(&lib_sem);
	print_stamp();
	printf("Student%ld is off to find books.\n", pthread_self());
	student_count -= 1;
	sem_post(&lib_sem);
}

book_t pick_a_book(){
	print_stamp();
	printf("Student%ld entering pick_a_book().\n", pthread_self());
	pthread_mutex_lock(&mutex);
	int i = rand() % book_search;
	int got_book = FALSE;
	if(book_count>0){
		while(got_book == FALSE){
			if(bookshelf[i].picked != TRUE){
				bookshelf[i].picked = TRUE;
				got_book = TRUE;
				book_count -= 1;
				if (DEBUG_MODE == TRUE)
				printf("\tStudent%ld found a book.\n", pthread_self());
				pthread_mutex_unlock(&mutex);
				return bookshelf[i];
			}else{
				i = rand() % book_search;
			}
		}
	}else{
		if (DEBUG_MODE == TRUE)
		printf("\tStudent %ld couldn't find a book.\n", pthread_self());
		pthread_mutex_unlock(&mutex);
		return no_book;
	}		
	print_stamp();
	printf("Student%ld exiting pick_a_book().\n", pthread_self());
}

void read_book(book_t book){
	print_stamp();
	printf("Student%ld entering read_book().\n", pthread_self());
	struct timeval x;
	struct timeval y;
	struct timeval d;
	gettimeofday(&x,NULL);
	if(book.pages != 0){
		float x ;
		x = exprand(T*book.pages);
		struct timespec ts;
		ts.tv_nsec = 1000*(x);
		if (DEBUG_MODE == TRUE)
		printf("\tStudent %ld is reading...\n", pthread_self());
		usleep(x);
	}else{
		if (DEBUG_MODE == TRUE)
		printf("\tStudent %ld has no book. :(\n", pthread_self());
		}
	gettimeofday(&y,NULL);
	print_stamp();
	printf("Student%ld exiting read_book(). ", pthread_self());
	timeval_subtract(&d,&y,&x);
	print_et(&d);	
}

/*Share_impressions uses a double turnstile to block threads[1].*/
int share_impressions(book_t book){
	print_stamp();
	printf("Student%ld entering share_impressions().\n", pthread_self());
	struct timeval x;
	struct timeval y;
	struct timeval d;
	gettimeofday(&x,NULL);
	pthread_mutex_lock(&mutex); 		/***start***/
		share_count += 1;  
		share_pages += book.pages;             	 
		if (share_count == N){  		/*Last one in activates session*/
			float t; 					/*and unlocks first barrier*/
			if (DEBUG_MODE == TRUE)        	  
			printf("\tSharing Info...\n");
			t = exprand(T*share_pages);
			usleep(t);
			share_pages = 0;			
			sem_wait(&share_2);
			sem_post(&share_1);
		}			  	  
	pthread_mutex_unlock(&mutex);		/****end****/
	
	sem_wait(&share_1);
	sem_post(&share_1);
	
	pthread_mutex_lock(&mutex);	 		/***start***/
		if(share_count == N){
			if(book_count == 0){
				finished = TRUE;		/*first thread checks bookshelf*/
			}else{
				sort_shelf();			/*bookshelf is sorted*/
			}
		}	
		share_count -= 1;               	 
		if (share_count == 0){ 
			if (DEBUG_MODE == TRUE)          	  
			printf("\tContinuing\n");
			sem_wait(&share_1);			/*locks first barrier*/
			sem_post(&share_2);			/*unlocks second*/
			}			  	  
	pthread_mutex_unlock(&mutex);		/****end****/
	
	sem_wait(&share_2);
	sem_post(&share_2);
	
	gettimeofday(&y,NULL);
	print_stamp();
	printf("Student%ld exiting share_impressions().", pthread_self());
	timeval_subtract(&d,&y,&x);
	print_et(&d);
	return finished;
}

void return_book(book_t book){
	print_stamp();
	printf("Student%ld entering return_book().\n", pthread_self());
	if(book.pages != 0){
		book = no_book;					/*discard book*/
		if (DEBUG_MODE == TRUE)
		printf("\tStudent%ld discarded thier book.\n", pthread_self());
	}
	print_stamp();
	printf("Student%ld exiting return_book().\n", pthread_self());
}

/*Creates books*/
void populate_shelf(){
	int i;
	int pagenum;
	for(i = 0; i < M; i++){
		pagenum = box_muller(400,50);	/*normal distribution*/
		bookshelf[i].pages = pagenum;
		bookshelf[i].picked = FALSE;
	}
}

/*Sorts the bookshelf array for efficient searching*/
void sort_shelf(){
	int i;
	book_t key;
	int pos;
	int unpicked = 0;
	for(i = 1; i < book_search; i++){
		key = bookshelf[i];
		pos = i;
		while(pos>0 && bookshelf[pos-1].picked > key.picked){
			bookshelf[pos] = bookshelf[pos-1];
			pos--;
		}
		bookshelf[pos] = key;
	}
	
	for(i=0;i<book_search;i++){
		if(bookshelf[i].picked == FALSE)
			unpicked++;
		}
	book_search = unpicked;				/*remaining unpicked books*/
}

/*Creates random floating point number between 0 and 1*/
float ranf(){
	 float r = (float)rand()/(float)RAND_MAX;
     return r;
}

/*Creates random float with exponential distribution (m = mean)*/
float exprand(float m){
	
	float x,u;
	m = 1/m;
	u = ranf();
	x = (-log(u))/m;
	return x;
}

/*Prints timestamps with microsecond accuracy.*/
void print_stamp(){
	char buffer [40];
	struct tm* tm;
	struct timeval tv;
	gettimeofday(&tv,NULL);
	tm = localtime(&tv.tv_sec);
	strftime(buffer, sizeof buffer,"%H:%M:%S", tm);
	printf("%s.%ld ",buffer,tv.tv_usec);
}

/*Prints elapsed time*/
void print_et(struct timeval *d){
	char buffer[40];
	struct tm* tm;
	tm = localtime(&d->tv_sec);
	strftime(buffer, sizeof buffer,"%H:%M:%S", tm);
	printf("Time taken: %ld.%ld \n",d->tv_sec,d->tv_usec);
}

int main(){
	int i;
	srand(time(NULL));
	no_book.pages = 0;
	no_book.picked = TRUE;
	pthread_t students[N];
	pthread_attr_t pt_attribs;
	sem_init(&lib_sem, 0, 0);
	sem_init(&share_1, 0, 0);
	sem_init(&share_2, 0, 1);
	pthread_mutex_init(&mutex, NULL);
	
	populate_shelf();
	
	for(i = 0 ; i < N; i++){
		pthread_attr_init(&pt_attribs);
		pthread_create(&(students[i]), &pt_attribs, student_thread, NULL);
	}
	
	if (SLEEP == 1) sleep(30);

    for (i = 0; i < N; i++){
        pthread_join(students[i], NULL);
    }
	printf("\tDone! No more books!\n");	
	pthread_exit(NULL);
	return 0;
}//end main

/* boxmuller.c           Implements the Polar form of the Box-Muller
                         Transformation

                      (c) Copyright 1994, Everett F. Carter Jr.
                          Permission is granted by the author to use
			  this software for any application provided this
			  copyright notice is preserved.
*/

int box_muller(float m, float s)	/* normal random variate generator */
{				        			/* mean m, standard deviation s */
	float x1, x2, w, y1;
	static float y2;
	static int use_last = 0;

	if (use_last)		        /* use value from previous call */
	{
		y1 = y2;
		use_last = 0;
	}
	else
	{
		do {
			x1 = 2.0 * ranf() - 1.0;
			x2 = 2.0 * ranf() - 1.0;
			w = x1 * x1 + x2 * x2;
		} while ( w >= 1.0 );
		printf("...");
		w = sqrt( (-2.0 * log( w ) ) / w );
		y1 = x1 * w;
		y2 = x2 * w;
		use_last = 1;
	}

	return(int)( m + y1 * s );
}

/*timeval_subtract taken from 
http://www.gnu.org/s/libc/manual/html%5Fnode/Elapsed-Time.html*/
 int
 timeval_subtract (result, x, y)
      struct timeval *result, *x, *y;
 {
   /* Perform the carry for the later subtraction by updating y. */
   if (x->tv_usec < y->tv_usec) {
     int nsec = (y->tv_usec - x->tv_usec) / 1000000 + 1;
     y->tv_usec -= 1000000 * nsec;
     y->tv_sec += nsec;
   }
   if (x->tv_usec - y->tv_usec > 1000000) {
     int nsec = (x->tv_usec - y->tv_usec) / 1000000;
     y->tv_usec += 1000000 * nsec;
     y->tv_sec -= nsec;
   }

   /* Compute the time remaining to wait.
      tv_usec is certainly positive. */

   result->tv_sec = x->tv_sec - y->tv_sec;
   result->tv_usec = x->tv_usec - y->tv_usec;
   

   /* Return 1 if result is negative. */
   return x->tv_sec < y->tv_sec;
 }
