// Assignment #: 2
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: This class reads an unspecified number of integers until the number 0
//               is entered and prints various information about the numbers.

import java.util.Scanner;

public class Assignment2
{
	public static void main (String[] args){

		int num  = 0;		// current number
		int max = 0;		// maximum number
		int psum = 0;		// sum of positive numbers
		int osum = 0;		// sum of odd numbers
		int ecount = 1;		// even number count. The 0 used to stop will always count.

		Scanner s = new Scanner(System.in);

		num = s.nextInt();	// reads user input

		while (num != 0){

			if (num > max){
				max = num;
			}

			if (num > 0){
				psum += num;
			}

			if (num%2 != 0){
				osum += num;
			} else {
				ecount++;
			}

			num = s.nextInt();
		}

		// Display output with number calculations
		System.out.println ("The maximum integer is " + max);
		System.out.println ("The sum of the positive integers is " + psum);
		System.out.println ("The sum of the odd integers is " + osum);
		System.out.println ("The count of even integers in the sequence is " + ecount);

	} //end main
}

