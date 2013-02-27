#include <stdio.h>
#include <malloc.h>
#include <pthread.h>
#include <math.h>

#define N 2048
#define SLEEP 5


int A[N][N];   /*input matrix A */
int B[N][N];   /*input matrix B */	
int C[N][N];
void *doyourpart(void *param);
void *doyourhalf(void *param);
int TT=1;
int rowCount;


int main(int argc, char *argv[])
{
	if(argc == 2){
		TT = atoi(argv[1]);
	}
    int i;
    int j;
	rowCount = N/TT;
	fillMatrices();

    int parts[TT];
    pthread_t tid[TT];
    pthread_attr_t pt_attribs;

    for (i=0; i<TT; i++)
    {
        parts[i] = i;
        pthread_attr_init(&pt_attribs);
                
		if(rowCount>0)
			pthread_create(&(tid[i]), &pt_attribs, doyourpart, &(parts[i]));
		else
			pthread_create(&(tid[i]), &pt_attribs, doyourhalf, &(parts[i]));
    }

    if (SLEEP == 1) sleep(30);

    for (i=0; i<TT; i++)
        pthread_join(tid[i], NULL);

    return 0;
}

int fillMatrices(){
     int row;	
     int col;
     for(row = 0; row < N; row++){
        for(col = 0; col < N; col++){
        	A[row][col] = 1;
	    	B[row][col] = 1;
		}
     }	
     return 0;
}


void *doyourpart(void *param)
{      
	int ptnum = *(int *)param;
	ptnum += 1;
    int row, col, start, end, k;
	start = (ptnum * rowCount) - rowCount;
	end = (ptnum * rowCount) - 1;
	
   	 for(row = start; row <= end; row++){
		for(col=0; col < N; col++){
			C[row][col]=0;
			for(k=0; k<=N; k++){
				C[row][col] += B[row][k]*A[k][col];
			}
		}
	}
	/*printf("Thread %d: Rows %d-%d complete.\n",ptnum-1,start,end);*/
	
    pthread_exit(0);
}

void *doyourhalf(void *param)
{   
	int ptnum = *(int *)param;
	ptnum += 1;    
 	int row, col, c_sta, c_end, cut,k;
	cut = N/2;
	
	if(ptnum>2){
		if ((ptnum%2)==0){
			row = ptnum/2 - 1;
		}else{
			row = (ptnum+1)/2 - 1;
		}
	}else{
		row = 0;
	}

	if((ptnum%2)==0){
		c_sta = cut;
		c_end = N-1;
	}else{
		c_sta = 0;
		c_end = cut-1;
	}
	
	for(col=c_sta; col <= c_end; col++){
		C[row][col]=0;
		for(k=0; k<=N; k++){
			C[row][col] += B[row][k]*A[k][col];
		}
	}
	
	/*printf("Thread %d: Row %d Cols %d-%d done.\n",ptnum-1,row,c_sta,c_end);*/
	
    pthread_exit(0);
}
