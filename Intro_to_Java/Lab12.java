/*///////////////////////////////////////////////////////////////// Lab 12 ///

 Dave Hubbard	3/24/09
 CSC 110		Arrays

 This lab is an introduction to arrays and how they are manipulated. The user
 is asked to enter numbers for two different arrays and their elements are
 multiplied to form a third array. The minimum and maximum values are then
 determined for each set, and sets of duplicates are counted.

////////////////////////////////////////////////////////////////////////////*/

import java.util.Scanner;

public class Lab12{
	public static void main(String[] args){

		Scanner console = new Scanner(System.in);
		int[] array1 = new int[5];
		int[] array2 = new int[5];
		int[] array3 = new int[5];
		int i = 0;

		System.out.println("Please enter five integers: ");
		while(i < 5){
			System.out.print("Integer "+ (i+1)+": ");
			array1[i] = console.nextInt();
			i++;
		}
		System.out.println();
		i = 0;
		System.out.println("Please enter five more integers: ");
		while(i < 5){
			System.out.print("Integer "+ (i+1)+": ");
			array2[i] = console.nextInt();
			i++;
		}
		System.out.println();
		i = 0;
		while(i < 5){
			array3[i] = array1[i]*array2[i];
			i++;
		}
		i = 0;
		System.out.print("\tSet 1\tSet 2\tProduct Set\n");
		while(i < 5){
		 	System.out.printf("\t%d\t%d\t%d\n",array1[i],array2[i],array3[i]);
			i++;
		}
		System.out.print("\n\tSet 1");
		minMax(array1);
		System.out.print("\n\tDuplicates: "+ countDuplicates(array1));

		System.out.print("\n\n\tSet 2");
		minMax(array2);
		System.out.print("\n\tDuplicates: "+ countDuplicates(array2));

		System.out.print("\n\n\tSet 3");
		minMax(array3);
		System.out.print("\n\tDuplicates: "+ countDuplicates(array3));
		System.out.println();


	}

    /*///////////////////////////////////////////////////////////// minMax ///
    /                                                                        /
    / Takes an array and finds the minimum and maximum numbers.              /
    ////////////////////////////////////////////////////////////////////////*/
	public static void minMax(int[] array){
		int min = array[0];
		int max = array[0];
		int i = 0;
		while(i < 5){
			if (array[i] < min){
				min = array[i];
			} else if (array[i] > max) {
				max = array[i];
			}
			i++;
		}
		System.out.print("\n\tMax = "+max);
		System.out.print("\n\tMin = "+min);
	}

    /*//////////////////////////////////////////////////// countDuplicates ///
    /                                                                        /
    / Counts the duplicate numbers in an array, and returns how many sets    /
    / of duplicates there are. With 5 element arrays, there are only a       /
    / maximum of 2 sets possible. Another array keeps track of numbers       /
    / already counted as duplicates.                                         /
    ////////////////////////////////////////////////////////////////////////*/
	public static int countDuplicates(int[] array){
		int[] blacklist = new int[2];
		int count = 0;
		int dup = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < 4){
			if(array[i] != blacklist[0] && array[i] != blacklist[k]){
				j = i;
				count = 0;
				while(j < 4){
					if(array[i] == array[j+1]){
						count++;
					}
					j++;
				}
				if(count>=1){
					dup += 1;
					blacklist[k]= array[i];
					k = 1;
				}
			}
			i++;
		}
		return dup;
	}




}//end of class
