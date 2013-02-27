/*/////////////////////////////////////////////////////////////////////
Dave Hubbard	03/11/09
CSC110			Lab11

This program takes HTML files, removes any HTML tags, and creates a new
file with the remaining text in .txt format.
/////////////////////////////////////////////////////////////////////*/


import java.util.Scanner;
import java.io.*;

public class Lab11{
	public static void main(String[] args)throws FileNotFoundException{

		Scanner console = new Scanner(System.in);
		System.out.print("Please enter an HTML file name: ");
		String filename = console.nextLine();
		System.out.println();
		String newname = newName(filename);
		stripTags(filename,newname);

	}

	// Takes a file and creates a single String out of the contents.//
	public static void stripTags(String filename, String newname)
				                     throws FileNotFoundException{

		Scanner file = new Scanner(new File(filename));
		String all = "";
		while(file.hasNextLine()){
			all += file.nextLine()+"\n";
		}
		noTags(newname,all);

	}

	// Creates a .txt file by writing a new String while ignoring//
	// any text between '<' and '>'.                             //
	public static void noTags(String name, String all)
						  throws FileNotFoundException{

		String notag = "";
		int i = 0;
		int m = all.length();

		PrintStream output = new PrintStream(new File(name));
		while(i<m){
			if(all.charAt(i)=='<'){
				notag += ("");
				while(all.charAt(i)!='>'){
					notag += ("");
					i++;
				}
				notag += ("");
				i++;
			} else {
				notag += all.charAt(i);
				i++;
			}
		}
		output.print(notag);
		System.out.println("The file " + name + " has been created!");
	}

	// Creates a .txt file name by creating a String based solely //
	// on characters that appear before the dot(.)                //
	public static String newName(String name){
		String newname = "";
		int i = 0;
		while(name.charAt(i)!='.'){
			newname += name.charAt(i);
			i++;
		}
		return (newname +"_NO_TAGS.txt");
	}

}//end of class

