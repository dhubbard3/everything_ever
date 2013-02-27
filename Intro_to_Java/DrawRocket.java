/***************************************************************************

CSC110 Programming Assignment #2
Dave Hubbard             2/10/09

This program draws a rocket of a certain size using a class constant.

****************************************************************************/

public class DrawRocket{

	public static final int SIZE = 2;

	public static void main(String[] args){
		cone();
		band();
		up();
		down();
		band();
		down();
		up();
		band();
		cone();
	}

	// Creates a cone //
	public static void cone(){
		int i=0;
		int j=0;
		for(i=1; i<= 2*SIZE-1; i++){
			for(j=1;j<=2*SIZE-i;j++){
				System.out.print(" ");
			}
			for(j=1;j<=i;j++){
				System.out.print("/");
			}
			System.out.print("**");
			for(j=1;j<=i;j++){
				System.out.print("\\");
			}
		System.out.println();
		}
	}

	// Creates a band //
	public static void band(){
		System.out.print("+");
		for(int i=1; i<=SIZE*2; i++){
			System.out.print("=*");
		}
		System.out.println("+");
	}

	// Creates a box of arrows pointing up //
	public static void up(){
		int i=1;
		int j=1;
		for(i=1; i<=SIZE; i++){
			System.out.print("|");
			for(j=1; j<=SIZE-i; j++){
				System.out.print(".");
			}
			for(int k=1; k<=i; k++){
				System.out.print("/\\");
			}
			for(int l=1; l<=(SIZE-i)*2; l++){
				System.out.print(".");
			}
			for(int k=1; k<=i; k++){
				System.out.print("/\\");
			}
			for(j=1; j<=SIZE-i; j++){
				System.out.print(".");
			}
		System.out.println("|");
		}
	}

	// Creates a box of arrows pointing down //
	public static void down(){
		int i=1;
		int j=1;
		for(i=1; i<=SIZE; i++){
			System.out.print("|");
			for(j=1; j<=i-1; j++){
				System.out.print(".");
			}
			for(int k=1; k<=SIZE-(i-1); k++){
				System.out.print("\\/");
			}
			for(int l=1; l<=(i-1)*2; l++){
				System.out.print(".");
			}
			for(int k=1; k<=SIZE-(i-1); k++){
				System.out.print("\\/");
			}
			for(j=1; j<=i-1; j++){
				System.out.print(".");
			}
		System.out.println("|");
		}
	}

}