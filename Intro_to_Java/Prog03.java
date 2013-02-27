/*//////////////////////////////////////////////////////////////////
Dave Hubbard	03/02/09
CSC110			Programming Assignment #3

This program analyzes and manipulates various types of user input in
interesting ways.
//////////////////////////////////////////////////////////////////*/

import java.util.Scanner;
import java.util.Random;

public class Prog03{
	public static void main(String[] args){

		Scanner console = new Scanner(System.in);

		System.out.print("If you would be so kind as to enter a string: ");
		String s = console.nextLine();
		System.out.println();
		System.out.print("Now, please enter a shorter fragment: ");
		String frag = console.nextLine();
		System.out.println();
		System.out.println("Thanks! Now I need two characters: ");
		System.out.print("Character 1: ");
		String s1 = console.next();
		System.out.print("Character 2: ");
		String s2 = console.next();
		System.out.println();
		System.out.println("Finally, plese enter three integers: ");
		System.out.print("Number 1: ");
		int n1 = console.nextInt();
		System.out.print("Number 2: ");
		int n2 = console.nextInt();
		System.out.print("Number 3: ");
		int n3 = console.nextInt();
		System.out.println();
		char c1 = (s1.charAt(0));
		char c2 = (s2.charAt(0));

		printDiamond(s);
		printRectangle(s);
		System.out.println("Palindrome?: "+ isPalindrome(s));
		System.out.println("Your string backwards: " + reverse(s));
		System.out.println("Uh-oh. Now it's jumbled: " + jumble(s));
		System.out.println("Swapping pairs is fun!: " + swapPairs(s));
		System.out.print("Your string has "+countVowels(s)+" vowels.");
		System.out.println(" You should be proud!");
		System.out.print("Your string has "+countConsonants(s)+" consonants.");
		System.out.println(" Lucky you!");
		System.out.println("Your string has all digits: "+ allDigits(s));
		System.out.println("Your string has all letters: "+ allAlpha(s));
		System.out.println("Look ma, no vowels!: " + stripVowels(s));
		System.out.println("Consonants? Why?: " + stripConsonants(s));
		System.out.print("All " + c1+ "'s replaced with "+ c2+ "'s!: ");
		System.out.println(replace(s,c1,c2));
		System.out.print("Your string has "+countChar(s,c1));
		System.out.println(" instances of the character " + c1+ ".");
		System.out.println("With feeling!: "+ multiString(s));
		System.out.println("All lowercase: "+ lowerCase(s));
		System.out.println("All caps: "+ upperCase(s));
		System.out.println("Alternating cases are fun!: "+ alterCase(s));
		System.out.println("Try to crack this code word: "+ encrypt(s));
		System.out.println("Ha ha! It's only: "+ decrypt(encrypt(s)));
		System.out.print("If "+ frag +" is in your string, ");
		System.out.println("it starts at index: "+ findInStr(s,frag)+".");
		System.out.println("Sum of 1/n+... "+n1+" times is: "+fractionSum(n1));
		System.out.println("The factors of "+n2+" are: ");
		printFactors(n2);
		System.out.println("The factorial of "+n3+" is: "+ factorial(n3));
		System.out.print("Your numbers in order are: ");
		printOrder(n1,n2,n3);

		System.out.println();
		System.out.println("Wasn't that fun!?");

	}

	//1.Draws a diamond based on a string parameter.//

	public static void printDiamond(String s){
		System.out.println("Your string as a diamond:");
		int m = s.length();
		for (int i=0; i<m; i++){
			for(int j=0; j<m-i; j++){
				System.out.print(" ");
			}
			System.out.print(s.charAt(i));
			for(int k=0; k<2*i-1; k++){
				System.out.print(" ");
			}
			if (i>=1){
				System.out.println(s.charAt(i));
			} else {
				System.out.println();
			}
		}

		for (int i=m-1; i>=0; i--){
			for(int j=m-i; j>0; j--){
				System.out.print(" ");
			}
			System.out.print(s.charAt(i));
			for(int k=2*i-1; k>0; k--){
				System.out.print(" ");
			}
			if (i>=1){
				System.out.println(s.charAt(i));
			} else {
				System.out.println();
			}
		}
		System.out.println();
	}

	//2.Draws a rectangle using a string parameter.//

	public static void printRectangle(String s){
		System.out.println("Your string as a rectangle:");
		int m = s.length();
		System.out.println(s);
		for (int i=1; i<=m-2; i++){
			System.out.print(s.charAt(i));
			for (int j=1; j<=m-2; j++){
				System.out.print(" ");
			}
			System.out.println(s.charAt(m-i-1));
		}
		System.out.println(reverse(s));
		System.out.println();
	}

	/*3.Tests to see if a string is a palindrome by testing
		the first half of a word with the last half. All
		palindromes are of an odd length, so the middlemost
		index is not tested.*/

	public static boolean isPalindrome(String s){
		int m = s.length();
		int half = m/2;
		int sum = 0;
		String left = s.substring(0,half);
		for (int i=0; i<half; i++){
			if (left.charAt(i)== s.charAt(m-i-1)){
				sum++;
			}
		}
		return(sum == half);
	}

	//4.Reverses a string.//

	public static String reverse(String s){
		int m = s.length();
		String rev = "";
		for (int i=m-1; i>=0; i--){
			 rev = rev+=(s.charAt(i));
		}
		return rev;
	}

	//5.Jumbles a string by swapping two random indices n1 and n2.//

	public static String jumble(String s){
		Random r = new Random();
		int n1 = r.nextInt(s.length());
		int n2 = r.nextInt(s.length());
		String jum = "";

		if(n1==n2 && n1>0){
			n2 -= 1;
		}
		if(n1==n2 && n1==0){
			n2 += 1;
		}

		char swap1 = s.charAt(n1);
		char swap2 = s.charAt(n2);
		for (int i=0; i<s.length(); i++){
			if (i==n1){
				jum+=(swap2);
			} else if (i==n2){
				jum+=(swap1);
			} else {
				jum+=(s.charAt(i));
			}
		}
		return jum;
	}

	/*6.Swaps every pair of indices in a string. If the
		string length is odd, the last index is left alone.*/

	public static String swapPairs(String s){
		int m = s.length();
		String pair = "";
		for (int i=0; i<m; i+=2){
			if (i <= m-2){
				pair +=(s.charAt(i+1));
				pair +=(s.charAt(i));
			} else {
				pair +=(s.charAt(i));
			}
		}
		return pair;
	}

	//7.Counts the number of vowels in a string.//

	public static int countVowels(String s){
		int m = s.length();
		int count = 0;
		for (int i=0; i<m; i++){
			if(vCheck(s.charAt(i))== true){
				count++;
			}
		}
		return count;
	}

	//8.Counts the number of consonants in a string.//

	public static int countConsonants(String s){
		int m = s.length();
		int count = 0;
		for (int i=0; i<m; i++){
			if(vCheck(s.charAt(i))== false){
				count++;
			}
		}
		return count;
	}

	//9.Checks to see if all characters of a string are digits.//

	public static boolean allDigits(String s){
		int m = s.length();
		int count = 0;
		for (int i=0; i<m; i++){
			if(s.charAt(i)>='0' && s.charAt(i)<='9'){
			count++;
			}
		}
		return(count == m);
	}

	//10.Checks to see if all characters of a string are letters.//

	public static boolean allAlpha(String s){
		int m = s.length();
		int count = 0;
		for (int i=0; i<m; i++){
			if(s.charAt(i)>='a' && s.charAt(i)<='z'){
				count++;
			} else if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
				count++;
			}
		}
		return(count == m);
	}

	//11.Omits the vowels in a string.//

	public static String stripVowels(String s){
		int m = s.length();
		String vless = "";
		for (int i=0; i<m; i++){
			if(vCheck(s.charAt(i))== true){
				vless += "";
			} else {
				vless +=(s.charAt(i));
			}
		}
		return vless;
	}

	//12.Omits the consonants in a string.//

	public static String stripConsonants(String s){
		int m = s.length();
		String cless = "";
		for (int i=0; i<m; i++){
			if(vCheck(s.charAt(i))== false){
				cless += "";
			} else {
				cless += (s.charAt(i));
			}
		}
		return cless;
	}

	//13.Replaces character 'c1' with 'c2' throughout the string.//

	public static String replace(String s, char c1, char c2){
		int m = s.length();
		String rep = "";
		for (int i=0; i<m; i++){
			if(s.charAt(i)==c1){
				rep += c2;
			} else {
				rep += s.charAt(i);
			}
		}
		return rep;
	}

	//14.Counts the number of 'char c' present in a string.//

	public static int countChar(String s, char c){
		int m = s.length();
		int count = 0;
		for (int i=0; i<m; i++){
			if(s.charAt(i)==c){
				count++;
			}
		}
		return count;
	}

	//15.Repeats the characters of a string based on the current index.//

	public static String multiString(String s){
		int m = s.length();
		String multi = "";
		for (int i=0; i<m; i++){
			for(int j=1; j<=m-i; j++)
			multi += s.charAt(i);
		}
		return multi;
	}

	//16.Converts a string to all lowercase letters.//

	public static String lowerCase(String s){
		int m = s.length();
		String low = "";
		for (int i=0; i<m; i++){
			if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
				low += (char)(s.charAt(i)+ 32);
			} else {
				low += s.charAt(i);
			}
		}
		return low;
	}

	//17.Converts a string to all uppercase letters.//

	public static String upperCase(String s){
		int m = s.length();
		String up = "";
		for (int i=0; i<m; i++){
			if(s.charAt(i)>='a' && s.charAt(i)<='z'){
				up += (char)(s.charAt(i)- 32);
			} else {
				up += s.charAt(i);
			}
		}
		return up;
	}

	//18.Converts a string to alternating cases starting from low.//

	public static String alterCase(String s){
		int m = s.length();
		String low = lowerCase(s);
		String alt= "";
		for (int i=0; i<m; i++){
			if(i%2 != 0 && alphaCheck(low.charAt(i)) == true){
				alt += (char)(low.charAt(i)-32);
			} else {
				alt += low.charAt(i);
			}
		}
		return alt;
	}

	/*19.Encrypts a string based on the following algorithm:
		 a) Subtracts 20 from the numeric code of each character.
		 b) Reverses the result.
		 c) Swaps the first and last character.*/

	public static String encrypt(String s){
		int m = s.length();
		String enc = "";
		for (int i=0; i<m; i++){
			enc += (char)(s.charAt(i)-20);
		}
		String code1 = reverse(enc);
		String code2 = firstSwap(code1);
		return code2;
	}


	//20.Decodes the encryption from method 19.//

	public static String decrypt(String s){
		int m = s.length();
		String dec = "";
		for (int i=0; i<m; i++){
			dec += (char)(s.charAt(i)+20);
		}
		String code1 = reverse(dec);
		String code2 = firstSwap(code1);
		return code2;
	}

	/*21.Checks if one string (s2) is present in another (s1),
		 and gives the index of where it starts.*/

	public static int findInStr(String s1, String s2){
		int m = s1.length();
		int n = s2.length();
		int idx = -1;
		boolean cc = false;
		for (int i=0; i<m; i++){
			if(s1.charAt(i)== s2.charAt(0) && i<=m-n){
				String check = s1.substring(i,i+n);
				cc = strEqual(check,s2);
				if(cc == true){
					idx = i;
				}
			}
		}
		return idx;
	}

	/*22.Returns the sum of the first n terms of the fraction sequence
		 1 + 1/2 + 1/3 + 1/4 + 1/5 +...*/

	public static double fractionSum(int n){
		double sum = 0;
		for (int i=1; i<=n; i++){
				sum +=  (1.0/i);
			}
		return sum;
	}

	/*23.Prints the factors of an integer n seperated by "and".
		If n is less than 0, no factors are returned.*/

	public static void printFactors(int n){
		if (n <= 0){
			System.out.println("There are no factors of this number...");
		} else {
			System.out.print(1);
			for (int i=2; i<=n; i++){
				if (n%i == 0){
					System.out.print(" and " + i);
				}
			}
		}
		System.out.println();
	}

	//24.Returns the factorial of an integer. //

	public static int factorial(int n){
		int fac = 1;
		for (int i=1; i<=n; i++){
			fac *= i;
		}
		return fac;
	}

	//25.Prints three integers in ascending order.//

	public static void printOrder(int a, int b, int c){
		int hi = 0;
		int mid = 0;
		int low = 0;
		if (a>b && a>c){
			hi = a;
			if(b>c){
				mid = b;
				low = c;
			} else {
				mid = c;
				low = b;
			}
		}
		if (b>a && b>c){
			hi = b;
			if(a>c){
				mid = a;
				low = c;
			} else {
				mid = c;
				low = a;
			}
		}
		if (c>a && c>b){
			hi = c;
			if(a>b){
				mid = a;
				low = b;
			} else {
				mid = b;
				low = a;
			}
		}
		System.out.println(low +", "+ mid +", "+ hi);
	}

	//  ~ Miscellaneous Methods ~ //

	//Checks to see if a character is a vowel.//

	public static boolean vCheck(char c){
		int v = 0;
		if (c=='a'|| c=='A'){
			v++;
		} else if (c=='e'|| c=='E'){
			v++;
		} else if (c=='i'|| c=='I'){
			v++;
		} else if (c=='o'|| c=='O'){
			v++;
		} else if (c=='u'|| c=='U'){
			v++;
		} else {
			v = 0;
		}
		return(v==1);
	}

	//Checks to see if a character is a letter.//

	public static boolean alphaCheck(char c){
		int a = 0;
		if (c>'a' && c<'z'){
			a++;
		} else if ( c>'A' && c<'Z'){
			a++;
		} else {
			a = 0;
		}
		return(a==1);
	}

	//Swaps the first and last indices in a string.//

	public static String firstSwap(String s){
		int m = s.length();
		char c = s.charAt(m-1);
		String swap = "";
		for (int i=0; i<m; i++){
			if (i == 0){
				swap += c;
			} else if (i == m-1){
				swap += s.charAt(0);
			} else {
				swap += s.charAt(i);
			}
		}
			return swap;
	}

	//Compares two strings for equality.//

	public static boolean strEqual(String s1, String s2){
		int m = s1.length();
		int comp = 0;
		for (int i=0; i<m; i++){
			if (s1.charAt(i) == s2.charAt(i)){
				comp++;
			}
		}
		return(comp==m);
	}

}



