/*//////////////////////////////////////////////// Programming Assignment 5 //

Dave Hubbard	04/11/09
CSC 110		Words.java

This program is a word game played between the user and the computer. The
computer will find a six letter word from a specific file, shuffle the letters
around, and then ask the user to create as many 3-6 letter words as possible
from the given letters in 150 seconds.

////////////////////////////////////////////////////////////////////////////*/

import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Words{

	public static final String FILE = "wordlist.txt";
	public static final int MIN = 3;
	public static final int SMALL = 4;
	public static final int LARGE = 5;
	public static final int MAX= 6;


	public static void main(String[] args)throws FileNotFoundException{
		System.out.println(" Loading dictionary. Please wait a moment...");
		String[] master = fileArray();
		Scanner kb = new Scanner(System.in);

		System.out.print("\n Hello! This is a game that will test your ");
		System.out.print("knowledge of words!\n The object is to create as ");
		System.out.print("many 3- to 6-letter words as you\n can with the ");
		System.out.print("given letters in 150 seconds.\n");
		System.out.print("\n Would you like to play (y or n)? : ");
		String input = kb.next();

		while (!input.equalsIgnoreCase("n")){
			input = gameFlow(kb,master);
		}
	}

    /*//////////////////////////////////////////////////////////// gameFlow //
    /                                                                        /
    / Regulates the overall flow of the game. Returns the user response      /
    / whether to or not continue the game.                                   /
    ////////////////////////////////////////////////////////////////////////*/
	public static String gameFlow(Scanner kb,String[] master){
		int timer = 1;
		String guess = "";
		String word = gameWord(master);
		String[] cpu = cpuArray(master,word);
		String[] player = userArray(cpu);
		String letters = wordScramble(word,word);
		System.out.print("\n\n The 6-letters are: "+ letters);
		System.out.print("\n Enter your words now! (Type '0' to give up)\n");
		long start = System.currentTimeMillis();
		timer = (gameTimer(start,System.currentTimeMillis(),""));

		while(timer>0){
			System.out.print("\n >> "+timer+" seconds left! -- ");
			letters = wordScramble(word,letters);
			System.out.print("Letters to use: "+ letters);
			System.out.print("\t>>: ");
			guess = kb.next();
			System.out.print(playArray(cpu,player,guess)+"\n");
			timer = (gameTimer(start,System.currentTimeMillis(),guess));
		}

		System.out.print("\n Oh, looks like time is up!\n\n");
		scores(cpu,player);
		System.out.print("\n\n Want to play again?(y or n): ");
		return kb.next();
	}

    /*/////////////////////////////////////////////////////////// gameTimer //
    /                                                                        /
    / Regulates the game timer. Returns '0' if the user enters '0'.          /
    ////////////////////////////////////////////////////////////////////////*/
	public static int gameTimer(long start,long current,String guess){
		int stime = (int)start/1000+150;
		int ctime = (int)current/1000;

		if(guess.equals("0"))
			return 0;
		else
			return (stime-ctime);
	}

    /*/////////////////////////////////////////////////////////// playArray //
    /                                                                        /
    / Matches the player's correct guesses up with the same position in the  /
    / cpu array. Gives multiple responses based on various situations.       /
    ////////////////////////////////////////////////////////////////////////*/
	public static String playArray(String[] cpu,String[]player,String guess){
		if(guess.equals("0"))
			return " You're giving up?";

		if(!guess.equals("")){
			for(int i=0; i<player.length; i++){
				if(guess.equalsIgnoreCase(player[i]))
					return " You already got that one!";
			}
		}

		for(int i=0; i<player.length; i++){
			if(guess.equalsIgnoreCase(cpu[i])){
				player[i]=cpu[i];
				return " Excellent!";
			}
		}
		return " Nope! Try Again!";
	}

    /*////////////////////////////////////////////////////////////// scores //
    /                                                                        /
    / Compares the arrays of the player and computer and prints the scores   /
    / and the number of words found for each.                                /
    ////////////////////////////////////////////////////////////////////////*/
	public static void scores(String[]cpu,String[]player){
		int cScore = 0;
		int cWords = cpu.length;
		int pScore = 0;
		int pWords = 0;

		for(int i=0; i<cpu.length; i++){
			if(player[i].length() >= MIN){
				pWords += 1;
				pScore += player[i].length();
			}
			cScore += cpu[i].length();
		}

		System.out.printf("  %8s\t%8s\n","COMPUTER","PLAYER");
		System.out.printf("  %8s\t%8s\n",cWords+" words",pWords+" words");
		System.out.printf("  %8s\t%8s\n",cScore+" points",pScore+" points");
		for(int i=0; i<cpu.length; i++){
			System.out.printf("  %8s\t%8s\n",cpu[i],player[i]);
		}
	}

    /*/////////////////////////////////////////////////////////// fileArray //
    /                                                                        /
    / Creates a master array based on the given file data.                   /
    ////////////////////////////////////////////////////////////////////////*/
	public static String[] fileArray()throws FileNotFoundException{
		Scanner file = new Scanner(new File(FILE));
		int i = 0;
		int count = 0;
		String read = "";

		while(file.hasNext()){
			read += file.next()+" ";
			count++;
		}

		String[] master  = new String[count];
		Scanner mlist = new Scanner(read);

		for(i=0; i<master.length; i++){
			master[i]= mlist.next();
		}

		return master;
	}

    /*//////////////////////////////////////////////////////////// gameWord //
    /                                                                        /
    / Chooses a random six letter word from the master array.                /
    ////////////////////////////////////////////////////////////////////////*/
	public static String gameWord(String[] master){
		Random r = new Random();
		int num = r.nextInt(master.length);
		String word = master[num];

		while(word.length() != MAX){
			num = r.nextInt(master.length);
			word = master[num];
		}
		return word;
	}

	/*//////////////////////////////////////////////////////// wordScramble //
	/                                                                        /
	/ Checks to see if the letters given match the original word and shuffles/
	/ the characters three times everytime the method is called.             /
    ////////////////////////////////////////////////////////////////////////*/
	public static String wordScramble(String word,String letters){
		boolean shuffle = true;
		while(letters.equals(word) || shuffle == true){
			letters=(charScramble(letters));
			letters=(charScramble(letters));
			letters=(charScramble(letters));
			shuffle = false;
		}
		return letters;
	}

    /*//////////////////////////////////////////////////////// charScramble //
    /                                                                        /
    / Flips two random characters of a string. Returns a jumbled string.     /
    ////////////////////////////////////////////////////////////////////////*/
	public static String charScramble(String s){
		Random r = new Random();
		String scramble = "";
		int n1 = r.nextInt(MAX);
		int n2 = r.nextInt(MAX);
		char c1 = s.charAt(n1);
		char c2 = s.charAt(n2);

		for(int j=0;j<MAX;j++){
			if(j==n1)
				scramble+= c2;
			else if(j==n2)
				scramble+= c1;
			else
				scramble+= s.charAt(j);
		}
		return scramble;
	}

	/*//////////////////////////////////////////////////////////// cpuArray //
    /                                                                        /
    / Accepts a six letter word and scans the master array to find words (of /
    / various lengths) that can be created with the letters of the original  /
    / word. Returns an array of these words in alphabetical order by length. /          													 /
    ////////////////////////////////////////////////////////////////////////*/
	public static String[] cpuArray(String[] master,String word){
		int count = 0;
		String finalList = "";

		String sml = wordList(master,word,MIN);
		String med = wordList(master,word,SMALL);
		String lrg = wordList(master,word,LARGE);
		String xlg = wordList(master,word,MAX);

		String countList = sml+med+lrg+xlg;
		Scanner counter = new Scanner(countList);

		while(counter.hasNext()){
			finalList += counter.next()+" ";
			count++;
		}

		String[] cpuList = new String[count];
		Scanner scan = new Scanner(finalList);

		for(int i=0;i<cpuList.length;i++){
			cpuList[i] = scan.next();
		}
		return cpuList;
	}

	/*/////////////////////////////////////////////////////////// userArray //
 	/                                                                        /
	/ Creates an array for the player's guesses. The size is based on the    /
	/ size of the cpu array.                                                 /
    ////////////////////////////////////////////////////////////////////////*/
	public static String[] userArray(String[] cpu){
		String[] player = new String[cpu.length];
		for(int i=0; i<cpu.length; i++){
			player[i]="";
		}
		return player;
	}

    /*//////////////////////////////////////////////////////////// wordList //
    /                                                                        /
    / Creates a string of words of a specific size using the letters from    /
    / the original word.                                                     /
    ////////////////////////////////////////////////////////////////////////*/
	public static String wordList(String[] master,String word, int size){
		String list = "";
		int count =0;

		for(int i=0; i<master.length; i++){
			count = 0;
			if(master[i].length()==size)
				count = charCheck(master[i],word,size);
			if(count == size)
				list += master[i]+" ";
		}
		return list;
	}

    /*/////////////////////////////////////////////////////////// charCheck //
    /                                                                        /
    / Checks a word of length 'size' to see if it can be created with the    /
    / letters of the original six-letter word.                               /
    ////////////////////////////////////////////////////////////////////////*/
	public static int charCheck(String check,String word,int size){
		boolean stop = false;
		int count = 0;
		int pos = 0;
		String temp = "";

		for(int i=0; i<size; i++){
			temp = "";
			stop = false;
			for(int j=0; j<MAX; j++){
				if(check.charAt(i)==word.charAt(j) && stop==false){
					count++;
					pos = j;
					stop = true;
				}
			}
			if(stop==true){
				for(int k=0; k<MAX; k++){
					if (k == pos)
						temp += "!";
					else
						temp += word.charAt(k);
				}
				word = temp;
			}
		}
		return count;
	}

}//end
