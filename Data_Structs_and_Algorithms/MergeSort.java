import java.io.*;

public class MergeSort{

	public static void main(String[] args){

		String file = args[0];
		System.out.print("Opening " + file + "...\n");
		FileReader fr = null;
		BufferedReader inFile = null;
		FileWriter fw = null;
		PrintWriter outFile = null;

		try{
			fr = new FileReader(file);
			inFile = new BufferedReader(fr);
			String line = inFile.readLine();
			int size = getInt(line);

			int[] num = new int[size];
			line = inFile.readLine();
			int i =0;


			while(!line.equals("")){
				num[i] = getInt(line);
				line = inFile.readLine();
				i++;
			}

			mergeSort(num,0,size-1);

			fw = new FileWriter("output.txt");
			outFile = new PrintWriter("output.txt");
			outFile.println(size);
			for(i=0;i<size;i++){
				outFile.println(num[i]);
			}
			outFile.close();

		}

		catch(FileNotFoundException ex){
			System.out.print("Error: File Not Found.");
			}
		catch(IOException ex){}
		System.out.print("Done!");
	}//end main

	public static int getInt(String s){
		int i = Integer.parseInt(s);
		return i;
	}

	public static void mergeSort(int[] num, int start, int end){
		if(start < end){
			int mid = (start+end)/2;
			mergeSort(num,start,mid);
			mergeSort(num,mid+1,end);
			merge(num,start,mid,end);
		}
	}//end mergeSort

	public static void merge(int[] num, int start, int mid, int end){
		Integer temp = new Integer(0);
		int max = temp.MAX_VALUE;
		int i, j;

		int n1 = mid-start+1;
		int n2 = end-mid;
		int[] left = new int[n1+1];
		int[] right = new int[n2+1];

		for(i=0; i<n1; i++){
			left[i] = num[start+i];
		}
		for(j=0; j<n2; j++){
			right[j] = num[mid+1+j];
		}
		left[n1] = max;
		right[n2] = max;
		i=0;
		j=0;

		for(int k=start; k<=end; k++){
			if(left[i]<=right[j]){
				num[k] = left[i];
				i++;
			} else {
				num[k] = right[j];
				j++;
			}
		}

	}//end merge



}//end class