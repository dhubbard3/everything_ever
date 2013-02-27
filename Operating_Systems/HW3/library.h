#define TRUE 1
#define FALSE 0

typedef struct booktype{
	int pages;
	int picked;
	} book_t;
	
void *student_thread(void*);
void meet_at_the_library();
book_t pick_a_book();
void read_book(book_t);
int share_impressions(book_t);
void return_book(book_t);
void populate_shelf();
void sort_shelf();
int box_muller(float,float);
float exprand(float);
void print_stamp();
void print_et(struct timeval*);
