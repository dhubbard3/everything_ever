/*///////////////////////////////////////////////////////////////// Lab #X ///

Dave Hubbard	5/06/09
CSC 110		Predator/Prey Simulation

This program sets up a world of Organisms (Ants and Doodlebugs) that move,
breed, and eat independently with the use of classes.

////////////////////////////////////////////////////////////////////////////*/

import java.util.Scanner;

public class Prog06{

	public static final int ANT = 0;

	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		World w = new World(20,20);
		String menu = "";
		int n = 1;
		w.display();
		w.stats();

		while(!menu.equalsIgnoreCase("q")){
			System.out.print("\n<ENTER>\tSimulate N time steps and display");
			System.out.print(" word (current = "+n+" ).\n");
			System.out.print("<N>\tSimulate N time steps before next");
			System.out.print(" display.\n");
			System.out.print("<Q>\tQuit the simulation.\n");

			menu = kb.nextLine();
			if(menu.equals(""))
				w.timeStep(n);
			if(menu.equalsIgnoreCase("n")){
				System.out.print("How many steps?: ");
				n = kb.nextInt();
				w.timeStep(n);
			}
		}
		w.finalStats();

	}


}//end