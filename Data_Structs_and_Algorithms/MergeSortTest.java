import java.io.*;

public class MergeSortTest{

	public static void main(String[] args){

		int size = 8;
		int[] test = new int[size];
		test[0]= 74;
		test[1]= 23;
		test[2]= 123;
		test[3]= 42;
		test[4]= 4;
		test[5]= 1;
		test[6]= 12;
		test[7]= 17;

	for(int i=0;i<size;i++){
			System.out.println(test[i]);
		}
		mergeSort(test,0,size-1);

		for(int i=0;i<size;i++){
			System.out.println(test[i]);
		}
	}//end main

	public static void mergeSort(int[] num, int start, int end){
		if(start < end){
			int mid = (start+end)/2;

			mergeSort(num,start,mid);
			mergeSort(num,mid+1,end);
			merge(num,start,mid,end);
		}
	}//end mergeSort

	public static void merge(int[] num, int start, int mid, int end){
		      int n1 = mid-start+1;  //get the length of the first half subarray
		      int n2 = end-mid;      //get the length of the second half subarray
		      int[] left = new int[n1];
		      int[] right = new int[n2];

		      //i is used for the array "left", j for "right", k for "num"
		      int i, j, k;

		      // copy the elements in the array "num" to the arrays
		      // "left" and "right"
		      for (i=0; i<n1; i++)
		         left[i] = num[start+i];

		      for (j=0; j<n2; j++)
		         right[j] = num[mid+1+j];

		      i=0;
		      j=0;
		      k=start;
		       while (i < n1 && j < n2)   //merge left and right into "num"
		        {
		           if (left[i] <= right[j])
		            {
		              num[k] = left[i];
		              i++;
		             }
		           else
		            {
		              num[k] = right[j];
		              j++;
		            }
		           k++;
		        }
		              if ( i == n1)
				       {
				         while (k <= end)
				          {
				              num[k] = right[j];
				              k++;
				              j++;
				           }
				        }
				      else if (j == n2)
				       {
				          while (k <= end)
				           {
				              num[k] = left[i];
				              k++;
				              i++;
				            }
				         }


	}//end merge



}//end class