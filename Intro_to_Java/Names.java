/*//////////////////////////////////////////////// Programming Assignment 4 //

Dave Hubbard	3/31/09
CSC110			Names.java

This program runs 3 special processess that take data from a file (names.txt)
and outputs the data in the form of a histogram. Process 1 makes a histogram
of a single name. Process 2 compares two names for a chosen decade, and
process 3 finds names with a specific rank for a chosen decade. Process 4 will
quit the program.
////////////////////////////////////////////////////////////////////////////*/

import java.util.Scanner;
import java.io.*;

public class Names{

	public static final int STARS = 73;
	public static final int LASTDECADE = 11;
	public static final int MIN = 1;
	public static final int MENUMAX = 4;
	public static final int RANKMAX = 999;
	public static final int FIRSTDEC = 1900;
	public static final String FILE = "names.txt";

	public static void main(String[] args)throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		int process = 0;
		boolean go = true;

		while(go == true){
			System.out.println("Please choose a task number from below:");
			System.out.print("\t1 - ");
			System.out.println("Check histogram of a name's popularity.");
			System.out.print("\t2 - ");
			System.out.println("Compare two names in a specific decade.");
			System.out.print("\t3 - ");
			System.out.print("Show which names had a certain rank for a");
			System.out.println(" specific decade.");
			System.out.print("\t4 - ");
			System.out.println("Exit Program.");
			process = limit(console,"Selection(1-4)?: ",1,4);
			go = menuChoice(console,process);
		}
	}

	/*////////////////////////////////////////////////////////// menuChoice //
	/                                                                        /
	/ Runs the processes from the list of choices in main().                 /
	////////////////////////////////////////////////////////////////////////*/
	public static boolean menuChoice(Scanner console, int process)
									 throws FileNotFoundException{
		String name = "";
		String name2 = "";
		String data = "";
		String data2 = "";
		int rank = 0;
		int decade = 0;

		if (process == 1){
			data = checkName(console,"Please enter a name: ");
			name = nameData(data);
			data = intData(data);
			histogram(name,data);
			return true;
		} else if(process == 2) {
			data = checkName(console,"First name?: ");
			name = nameData(data);
			data = intData(data);
			data2 = checkName(console,"Second name?: ");
			name2 = nameData(data2);
			data2 = intData(data2);
			decade = selectDecade(console);
			compare(name,data,name2,data2,decade);
			return true;
		} else if(process == 3) {
			rank = limit(console,"What rank of popularity?: ",1,999);
			decade = selectDecade(console);
			nameRank(rank, decade);
			return true;
		}
		return false;
	}

	/*/////////////////////////////////////////////////////////////// stars //
	/                                                                        /
	/ Creates a certain number of stars based on rank number.                /
	////////////////////////////////////////////////////////////////////////*/
	public static void stars(int n){
		double i = 0;
		int j = 1;
		System.out.printf("%4d-",n);

		if(n>0){
			i = (1000.0-n)/999*STARS;
			i = (int)i+1;
		}

		while(j<=i){
			System.out.print("*");
			j++;
		}
		System.out.println();
	}

	/*/////////////////////////////////////////////////////////// checkName //
	/                                                                        /
	/ Checks to see if a user input name is found in the file and returns    /
	/ data. If a name is not found, the user is prompted for another.        /
	////////////////////////////////////////////////////////////////////////*/
	public static String checkName(Scanner s,String prompt)
									      throws FileNotFoundException{
		System.out.print(prompt);
		String name = s.next();
		String check = findName(name);
		while(check.equals("Not Here.")){
			System.out.print("Name not found. Try again.\n\n");
			System.out.print(prompt);
			name = s.next();
			check = findName(name);
		}
		return check;
	}

	/*//////////////////////////////////////////////////////////// findName //
	/                                                                        /
	/ Finds a specific name in the file and sends the data back.             /
	////////////////////////////////////////////////////////////////////////*/
	public static String findName(String name) throws FileNotFoundException{
		Scanner file = new Scanner(new File(FILE));
		String read = "";
		String write = "";

		while(file.hasNext()){
			read = file.nextLine()+"\n";
			Scanner rscan = new Scanner(read);
			if(rscan.next().equalsIgnoreCase(name))
				write = read;
		}
		if(write.equals(""))
			return("Not Here.");
		else
			return write;
	}

	/*//////////////////////////////////////////////////////////// nameData //
	/                                                                        /
	/ Pulls a name from the results of findName.                             /
	////////////////////////////////////////////////////////////////////////*/
	public static String nameData(String s){
		Scanner ss = new Scanner(s);
		String name = ss.next();
		return name;
	}

	/*///////////////////////////////////////////////////////////// intData //
	/                                                                        /
	/ Creates a string of all integers for data processing.                  /
	////////////////////////////////////////////////////////////////////////*/
	public static String intData(String s){
		Scanner ss = new Scanner(s);
		String ints = ss.next();
		ints = "";
		while(ss.hasNextInt()){
				ints += ss.nextInt()+" ";
		}
		return ints;
	}

	/*/////////////////////////////////////////////////////////// histogram //
	/                                                                        /
	/ Creates a histogram using a name and its integer data.                 /
	////////////////////////////////////////////////////////////////////////*/
	public static void histogram(String name, String ints){
		Scanner data = new Scanner(ints);
		int i = 0;
		System.out.println("Name: "+ name);
		System.out.println();
		while(i < LASTDECADE){
			int j = data.nextInt();
			stars(j);
			i++;
		}
		System.out.println();
	}

	/*////////////////////////////////////////////////////////////// getInt //
	/                                                                        /
	/ Checks to see if user input is an integer.                             /
	////////////////////////////////////////////////////////////////////////*/
	public static int getInt(Scanner console, String prompt){
		String eat = "";
		System.out.print(prompt);
		while(!console.hasNextInt()){
			eat = console.next();
			System.out.println("Not a valid number. Try again.");
			System.out.print(prompt);
		}
		return console.nextInt();
	}

	/*/////////////////////////////////////////////////////////////// limit //
	/                                                                        /
	/ Checks to see if a number falls within a certain range.                /
	////////////////////////////////////////////////////////////////////////*/
	public static int limit(Scanner s, String prompt, int min, int max){
		int i = (getInt(s,prompt));
		while(i < min || i > max){
			System.out.println("Out of range. Try again.");
			i = (getInt(s,prompt));
		}
		return i;
	}

	/*///////////////////////////////////////////////////////////// decData //
	/                                                                        /
	/ Grabs data from a specific decade (1 indicates 1900-1910, 2 is for     /
	/ 1910-1920 and so on until decade 11, 2000-Present                      /
	////////////////////////////////////////////////////////////////////////*/
	public static int decData(String s, int dec){
		Scanner scan = new Scanner(s);
		int i = MIN;
		int data = 0;
		while(i <= dec){
			data = scan.nextInt();
			i++;
		}
		return data;
	}

	/*///////////////////////////////////////////////////////// printDecade //
	/                                                                        /
	/ Prints a given decade. Used for output only.                           /
	////////////////////////////////////////////////////////////////////////*/
	public static String printDecade(int i){
		int first = (FIRSTDEC+(i-1)*10);
		int second = (first+10);
		String decade = "";
		if (i < LASTDECADE){
			decade = first +"-"+ second;
		} else {
			decade = first +"- :";
		}
		return decade;
	}
	/*//////////////////////////////////////////////////////// selectDecade //
	/                                                                        /
	/ Selects a decade from a numbered list (1-11) and checks                /
	/ user input to make sure the selection is legit.                        /
	////////////////////////////////////////////////////////////////////////*/
	public static int selectDecade(Scanner s){
			System.out.println("Choose a decade:");
			int j = 1;
			while(j <= LASTDECADE){
					System.out.println("\t"+j+" - "+printDecade(j));
					j++;
				}
			int i = limit(s,"Please enter a decade: ",MIN,LASTDECADE);
			while(i < MIN && i > LASTDECADE){
				System.out.println("Out of range.");
				i = limit(s,"Please enter a number(1-11): ",MIN,LASTDECADE);
			}
			return i;
	}

	/*///////////////////////////////////////////////////////////// compare //
	/                                                                        /
	/ Compares two names and their data for a specific decade. Prints a      /
	/ histogram of the two names for that decade.                            /
	////////////////////////////////////////////////////////////////////////*/
	public static void compare(String n1,String d1,String n2,String d2,int i){
		System.out.print("\nComparing "+n1+" and "+n2+" from ");
		System.out.print(printDecade(i)+": \n");
		System.out.println(n1+":");
		int first = decData(d1,i);
		stars(first);
		System.out.println(n2+":");
		int second = decData(d2,i);
		stars(second);
		System.out.println();
	}

	/*//////////////////////////////////////////////////////////// nameRank //
	/                                                                        /
	/ Takes a rank number and decade number and finds the names in the file  /
	/ that have the rank number for the chosen decade. Outputs two names.    /
	////////////////////////////////////////////////////////////////////////*/
	public static void nameRank(int rank, int decade)
						throws FileNotFoundException{

		Scanner file = new Scanner(new File(FILE));
		String read = "";
		String data = "";
		String name = "";
		String first= "";
		String last = "";
		int info = 0;

		while(file.hasNext()){
			read = file.nextLine()+"\n";
			name = nameData(read);
			data = intData(read);
			info = decData(data,decade);

			if(info == rank){
				if(first!="")
					last = name;
				else
					first = name;
			}
		}

		System.out.print("From "+printDecade(decade)+" the names with ");
		System.out.print("popularity rank "+rank+" were: \n");
		System.out.print("- "+first+"\n- "+last+"\n\n");
	}

} //end
