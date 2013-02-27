/***************************************************************************

CSC110 Programming Assignment #2
Dave Hubbard             2/17/09

This program gives the first nth numbers of the Fibonacci sequence (up to 46)
depending on a class constant.

****************************************************************************/

public class Fibonacci{

	public static final int SIZE = 9;

	public static void main(String[] args){

		int j=1;
		int k=1;
		int sum = 0;

		System.out.println("Fibonacci sequence with " + SIZE + " elements: ");
		System.out.println();

		for(int i=1; i<=SIZE; i++){
			System.out.println(" "+ k);
			sum = j + k;
			k = j;
			j = sum;
		}
	}

}