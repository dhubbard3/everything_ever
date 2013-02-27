import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Hash{

	public static void main(String args[]){
		String file = args[0];
			FileReader fr = null;
			BufferedReader inFile = null;
			FileWriter fw = null;
			PrintWriter outFile = null;
			LList[] chainList = new LList[23];
			int[] linearList = new int[32];
			int[] quadList = new int[32];
			int[] doubleList = new int[31];
			formatList(linearList);
			formatList(quadList);
			formatList(doubleList);

		try{
			fr = new FileReader(file);
			inFile = new BufferedReader(fr);
			String line = inFile.readLine();
			int read = getInt(line);
			int key;

			for(int i = 0; i < read; i++){
				line = inFile.readLine();
				key = getInt(line);
				insertChain(chainList,key);
				insertKey(linearList,key,'l');
				insertKey(quadList,key,'q');
				insertKey(doubleList,key,'d');
			}

			line = inFile.readLine();
			key = getInt(line);

			fw = new FileWriter("chain.txt");
			outFile = new PrintWriter(fw);
			printChain(chainList,outFile);
			searchChain(chainList,key,outFile);
			outFile.close();

			fw = new FileWriter("linear.txt");
			outFile = new PrintWriter(fw);
			printList(linearList,outFile);
			searchKey(linearList,key,'l',outFile);
			outFile.close();

			fw = new FileWriter("quadratic.txt");
			outFile = new PrintWriter(fw);
			printList(quadList,outFile);
			searchKey(quadList,key,'q',outFile);
			outFile.close();

			fw = new FileWriter("double.txt");
			outFile = new PrintWriter(fw);
			printList(doubleList,outFile);
			searchKey(doubleList,key,'d',outFile);
			outFile.close();
		}
		catch(FileNotFoundException ex){
			System.out.print("Error: File Not Found.");
		}
		catch(IOException ex){}
	}

	public static void insertChain(LList[] list, int key){
		int j = key % 23;
		if(list[j] == null){
			list[j] = new LList();
			list[j].addElem(key);
		}
		else{
			list[j].addElem(key);
		}
	}

	public static void insertKey(int[] list, int key, char type){
		int i=0;
		int j=0;
		int k;
		int k2;

		switch(type){
			//for linear probing
			case 'l':
						k = (int)(32 * mod1(key*0.618));
						for(i=0;i<list.length;i++){
							j = (k+i) % 32;
							if(list[j] == -1){
								list[j] = key;
								break;
							}
						}
						if(i == list.length)
							hashOverflow(key);
						break;
			//quadratic
			case 'q':
						k = (int)(32 * mod1(key*0.618));
						for(i=0;i<list.length;i++){
							j = (int)(k + (0.5*i) + (0.5*(i^2))) % 32;
							if(list[j] == -1){
								list[j] = key;
								break;
							}
						}
						if(i == list.length)
							hashOverflow(key);
						break;
			//double
			case 'd':
						k = key % 31;
						k2 = 1 + (key % 30);
						for(i=0;i<list.length;i++){
							j = (k + (i*k2)) % 31;
							if(list[j] == -1){
								list[j] = key;
								break;
							}
						}
						if(i == list.length)
							hashOverflow(key);
						break;
		}
	}

	public static void searchKey(int[] list, int key, char type, PrintWriter pw){
		int i=0;
		int j=0;
		int k;
		int k2;

		switch(type){
			//for linear probing
			case 'l':
						k = (int)(32 * mod1(key*0.618));
						for(i=0;i<list.length;i++){
							j = (k+i) % 32;
							pw.print(j + " ");
							if(list[j] == key){
								break;
							}
						}
						if(i == list.length)
							pw.println("-1");
						break;
			//quadratic
			case 'q':
						k = (int)(32 * mod1(key*0.618));
						for(i=0;i<list.length;i++){
							j = (int)(k + (0.5*i) + (0.5*(i^2))) % 32;
							pw.print(j + " ");
							if(list[j] == key){
								break;
							}
						}
						if(i == list.length)
							pw.println("-1");
						break;
			//double
			case 'd':
						k = key % 31;
						k2 = 1 + (key % 30);
						for(i=0;i<list.length;i++){
							j = (k + (i*k2)) % 31;
							pw.print(j + " ");
							if(list[j] == key){
								break;
								}
						}
						if(i == list.length)
							pw.println("-1");
							break;
			}
	}

	public static void printList(int[] list, PrintWriter pw){
		for (int i=0; i<list.length; i++){
			pw.println(i + "\t" + list[i]);
		}
		pw.println("-1");
	}

	public static void searchChain(LList[] list, int key, PrintWriter pw){
		int i;
		boolean keyFound = false;
		for(i = 0; i < list.length; i++){
			keyFound = list[i].searchElem(key);
			if(keyFound = true){
				pw.print(i + "\t");
				list[i].printKey(key,pw);
				break;
			}
		}
	}

	public static void printChain(LList[] list, PrintWriter pw){
		int i;
		for(i = 0; i < list.length; i++){
			pw.print(i + "\t");
			if(list[i] != null){
				list[i].printList(pw);
			}else{
				pw.println();
			}
		}
		pw.println("-1");
	}

	public static void hashOverflow(int key){
		System.out.println("Hash overflow occured for value " + key);
	}

	public static double mod1(double inp){
	  NumberFormat formatter = new DecimalFormat("#0.0#########");
	  String tmpStr = formatter.format(inp);
	  String fpart = "0.";
	  boolean fpartstarted = false;
	  for (int i=0; i<tmpStr.length(); i++){
	    if (tmpStr.charAt(i)=='.')
	       fpartstarted = true;
	    else if (fpartstarted)
	       fpart = fpart + tmpStr.charAt(i);
	  }
	  if (fpart=="0.")
	    fpart = "0.0";
	  return Double.parseDouble(fpart);
	}

	public static int getInt(String s){
		int i = Integer.parseInt(s);
		return i;
	}

	public static void formatList(int[] list){
		for (int i=0; i<list.length; i++){
			list[i] = -1;
		}
	}

}//end class