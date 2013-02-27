#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int matrix_size_sel = -1;
int matrix_size = 1;
char c[30];
char c4[30];
char c16[30];
char c64[30];
char c256[30];

int main(int argc, char *argv[]){
	
	if(argc==2){
	strcpy(c,"time -f %E ./");		
	strcat(c,argv[1]);
	strcpy(c4,c);
	strcpy(c16,c);
	strcpy(c64,c);
	strcpy(c256,c);
	strcat(c4," 4");
	strcat(c16," 16");
	strcat(c64," 64");
	strcat(c256," 256");
	}
	printf("File is: %s", argv[1]);
	test();

	return 0;
}

int test(){
	int i;

	printf("\n-------------------4\n"); 
	sleep(1);
	for(i=0;i<5;i++){
		system(c4);
	}

	printf("\n-------------------16\n");  
	sleep(1);
	for(i=0;i<5;i++){
		system(c16);
	}

	printf("\n-------------------64\n");   
	sleep(1);
	for(i=0;i<5;i++){
		system(c64);
	}

	printf("\n-------------------256\n");  
	sleep(1);
	for(i=0;i<5;i++){
		system(c256);
	}

	return 0;
}

