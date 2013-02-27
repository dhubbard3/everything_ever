/***************************************************************************

CSC110 Programming Assignment #2
Dave Hubbard             2/13/09

This program draws a number of stick figures standing on stairs of a certain
size relative to a class constant.

****************************************************************************/

public class DrawStairs{

	public static final int SIZE = 5;
	static int i=1;

	public static void main(String[] args){
		stairs();
		lastLine();
	}

	// Draws the figures and stairs //
	public static void stairs(){
		int j=0;
		for(i=1; i<=SIZE; i++){
			spaces1();
			System.out.print("  O  ******");
			spaces2();

			spaces1();
			System.out.print(" /|\\ *");
			spaces3();

			spaces1();
			System.out.print(" / \\ *");
			spaces3();
		}
	}

	// Draws the spaces before the figure //
	public static void spaces1(){
		for(int j=1; j<=(SIZE-i)*5; j++)
			System.out.print(" ");
	}

	// Draws the number of spaces adjacent to the head of the figure //
	public static void spaces2(){
		for(int j=1; j<=5*(i-1); j++)
			System.out.print(" ");
		System.out.println("*");
	}

	// Draws the number of spaces adjacent to the body and legs //
	public static void spaces3(){
		for(int j=1; j<=5*i; j++)
			System.out.print(" ");
		System.out.println("*");
	}

	// Completes the last row of stars //
	public static void lastLine(){
		System.out.print("******");
		for(int j=1; j<=SIZE*5; j++)
			System.out.print("*");
		System.out.println("*");
	}

}