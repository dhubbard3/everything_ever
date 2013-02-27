/*//////////////////////////////////////////////////////

Dave Hubbard 	03/11/09
CSC110			Lab10

This program takes multiple user generated integers and
gives the maximum and minimum using 3 different methods.

//////////////////////////////////////////////////////*/

import java.util.Scanner;

public class Lab10{
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);

		System.out.print("How many numbers would you like to enter?: ");
		int num = console.nextInt();

		System.out.println();
		System.out.println("(Using a for loop)");
		enterNumbers(console,num);

		System.out.println();
		System.out.println("(Using a while loop)");
		enterNumbersWhile(console,num);

		System.out.println();
		System.out.println("(User choice)");
		enterNumbersQuit(console);
	}

	// Takes user generated numbers and gives the max and min.
	// This method uses a for loop.
	public static void enterNumbers(Scanner number,int num){
		int max = 0;
		int min = 0;

		if (num>0){
			for(int i=1; i<=num; i++){
				System.out.print("Number "+ i +": ");
				int n = number.nextInt();
				if (i == 1){
					max = n;
					min = n;
				} else if (n>max) {
					max = n;
				} else if (n<min) {
					min = n;
				}
			}

			System.out.println("Smallest number: "+ min);
			System.out.println("Largest number: "+ max);
		} else {
			System.out.println("You chose not to enter any numbers.");
		}
	}

	// This method uses a while loop.
	public static void enterNumbersWhile(Scanner number,int num){

		int max = 0;
		int min = 0;
		int i = 1;

		if (num>0){
			while(i <= num){
				System.out.print("Number "+ i +": ");
				int n = number.nextInt();
				if (i == 1){
					max = n;
					min = n;
				} else if (n>max) {
					max = n;
				} else if (n<min) {
					min = n;
				}
				i++;
			}

			System.out.println("Smallest number: "+ min);
			System.out.println("Largest number: "+ max);
		} else {
			System.out.println("You chose not to enter any numbers.");
		}
	}

	// This method takes numbers until the user enters "quit".
	public static void enterNumbersQuit(Scanner number){

		int max = 0;
		int min = 0;
		int i = 1;
		String quit = "";

		while(!quit.equals("quit")){
			System.out.print("Enter an integer(or \"quit\" to exit): ");

			if (number.hasNextInt()){
				int n = number.nextInt();
				if (i == 1){
					max = n;
					min = n;
				} else if (n>max) {
					max = n;
				} else if (n<min) {
					min = n;
				}
				i++;
			} else if (number.next().toLowerCase().equals("quit")){
				quit = "quit";
			} else {
				System.out.println("Please try again.");
			}
		}
		if(i!=1){
			System.out.println("Smallest number: "+ min);
			System.out.println("Largest number: "+ max);
		}

	}
}