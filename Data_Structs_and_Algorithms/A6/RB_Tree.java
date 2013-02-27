import java.io.*;
import java.util.Scanner;

public class RB_Tree{

	public static void main(String[] args){

		String file = args[0];
		FileReader fr = null;
		BufferedReader inFile = null;
		FileWriter fw = null;
		PrintWriter outFile = null;

		try{
			fr = new FileReader(file);
			inFile = new BufferedReader(fr);
			String line = inFile.readLine();
			Scanner s = new Scanner(line);
			int next = s.nextInt();
			int key;
			int color;
			Tree T = new Tree();

			while(next != -1){
				color = next;
				next = s.nextInt();
				key = next;
				insert(T,key,color);
				line = inFile.readLine();
				s = new Scanner(line);
				next = s.nextInt();
			}

			line = inFile.readLine();
			s = new Scanner(line);
			next = s.nextInt();
			int end = next;

			for(int i=0; i < end; i++){
				line = inFile.readLine();
				s = new Scanner(line);
				next = s.nextInt();
				color = next;
				next = s.nextInt();
				key = next;
				manipulate(T,key,color);
			}

			fw = new FileWriter("output.txt");
			outFile = new PrintWriter("output.txt");
			printTree(T, outFile);
			outFile.println("-1");
			outFile.close();

		}
		catch(FileNotFoundException ex){
			System.out.print("Error: File Not Found.");
		}
		catch(IOException ex){}
	}

	public static void insert(Tree T,int key,int color){
		T.bstInsert(key,color);
	}

	public static void manipulate(Tree T, int key, int color){
		if (color == 0){
			T.rbInsert(key);
		} else {
			T.rbDelete(key);
		}
	}

	public static void printTree(Tree T, PrintWriter outFile){
		T.print(T.getRoot(), outFile);
	}


}//end class