#include <sys/shm.h>
#include <sys/wait.h>
#include <malloc.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <error.h>

#define N 2048 /* matrix size NxN */

typedef struct matrixstruct {
        int C[N][N];
        } matstr;

matstr *Cms; /* pointer to output matrix C */
int A[N][N];   /*input matrix A */
int B[N][N];   /*input matrix B */	
int Cid;     /* segment id for output matrix C */
int base_pid;
int M=1;

int main(int argc, char *argv[])
{
	if(argc == 2){
		M = atoi(argv[1]);
	}    
	int i;
	int rowCount = N/M;
    fillMatrices();

    /*
    *
    * STEP 1: create the shared segment
    */
    if ((Cid = shmget(IPC_PRIVATE, sizeof(matstr), IPC_CREAT | 0666)) < 0)
    {
        perror("smget returned -1\n");
        error(-1, errno, " ");
        exit(-1);
    }
    /*printf("Allocated %d, at id %d\n", (int) sizeof(matstr), Cid);*/

 
    /*
    *
    * STEP 2: fork the children processes
    */
    base_pid = getpid();
    for(i=1; i<M; i++) {
        if (getpid()==base_pid)
            fork();
        else
            break;
    }


    /*
    *
    * STEP 3: each child process attaches the segment
    */
    if ((Cms = (matstr *) shmat(Cid, NULL, 0)) == (matstr *) -1){
        perror("Process shmat returned NULL\n");
        error(-1, errno, " ");
    }
    else
        /*printf("Process %d attached the segment %d\n", getpid(), Cid);

    /*
    *
    *
    *
    * STEP 4: use the shared segment
    *
    *
    *
    */
	if(rowCount>0){    
		if(getpid()==base_pid){
			multMatrix(i,rowCount);
		}else{
			multMatrix(i-1,rowCount);
		}
	}else{
		if(getpid()==base_pid){
			halfMatrix(i);
		}else{
			halfMatrix(i-1);
		}
	}

    /*
    *
    * STEP 5: each child process detaches the segment
    */
    if (shmdt(Cms) == -1){
        perror("shmdt returned -1\n");
        error(-1, errno, " ");
    }else
       /* printf("Process %d detached the segment %d\n", getpid(), Cid);

    /*
    *
    * STEP 6: each child process exits and the parent joins
    */
    if (getpid()==base_pid)
        for(i=1; i<M; i++) {
            wait(NULL);
        }
    else
        exit(0);
	

    /*
    *
    * STEP 7: base process deletes the segment
    */
    if (shmctl(Cid,IPC_RMID,NULL) == -1){
        perror("shmctl returned -1\n");
        error(-1, errno, " ");
    }
 
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

int multMatrix(int ptnum, int rowCount){
    int row, col, start, end, k;
	start = (ptnum * rowCount) - rowCount;
	end = (ptnum * rowCount) - 1;
	
    for(row = start; row <= end; row++){
		for(col=0; col < N; col++){
			Cms->C[row][col]=0;
			for(k=0; k<=N; k++){
				Cms->C[row][col] += B[row][k]*A[k][col];
			}
		}
	}
	/*printf("Process %d: Rows %d-%d complete.\n",getpid(),start,end);*/
	
     return 0;
}

int halfMatrix(int ptnum){
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
		Cms->C[row][col]=0;
		for(k=0; k<=N; k++){
			Cms->C[row][col] += B[row][k]*A[k][col];
		}
	}
	
	/*printf("Process %d: Row %d Cols %d-%d done.\n",getpid(),row,c_sta,c_end);*/
	
     return 0;
}
	
