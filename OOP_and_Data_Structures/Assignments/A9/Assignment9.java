// Assignment #: 9
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: This class takes numbers entered by the user and runs various recursive
//				 methods using the information, such as finding the minimum number and
//				 counting the number of negative inputs.

import java.io.*;
import java.text.*;

public class Assignment9{

	public static void main(String[] args){

		double[] numbers = new double[100]; //User entered numbers will be placed here.
		double current = -1;
		String read = null;
		int i = 0;
		DecimalFormat fmt1 = new DecimalFormat("0.##");
		DecimalFormat fmt2 = new DecimalFormat("0.0#");
		DecimalFormat fmt3 = new DecimalFormat("0.000");

		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			while(current != 0){
				read = br.readLine();
				current = Double.parseDouble(read);
				numbers[i] = current;
				i++;
			}
		}
		catch(IOException ex){};

		double n1 = findMin(numbers,0,numbers.length-1);
		double n2 = computePositiveSum(numbers,0,numbers.length-1);
		double n3 = computeSumAtOdd(numbers,0,numbers.length-1);
		int    n4 = countNegative(numbers,0,numbers.length-1);

		System.out.println("The minimum number is " + fmt1.format(n1));
		System.out.println("The sum of the positive numbers is " + fmt2.format(n2));
		System.out.println("The sum of the numbers at odd indexes is " + fmt3.format(n3));
		System.out.println("The total count of negative numbers is " + n4);
	}

	// Finds the minimum value in the array.
	public static double findMin(double[] numbers, int startIndex, int endIndex){

		double result;

		if(startIndex == endIndex){
			return numbers[startIndex];
		}
		else
		{
			int mid = (startIndex + endIndex)/2;
			double min1 = findMin(numbers, startIndex, mid);
			double min2 = findMin(numbers, mid+1, endIndex);
			if(min1>min2)
				result = min2;
			else
				result = min1;
		}
		return result;
	}

	// Computes the sum of all positive numbers entered by the user.
	public static double computePositiveSum(double[] numbers, int startIndex, int endIndex){

		double result = 0;

		if(startIndex == endIndex){
			if (numbers[startIndex] > 0)
			result += numbers[startIndex];
		}
		else
		{
			int mid = (startIndex + endIndex)/2;
			double sum1 = computePositiveSum(numbers, startIndex, mid);
			double sum2 = computePositiveSum(numbers, mid+1, endIndex);
			if(sum1 > 0)
				result += sum1;
			if(sum2 > 0)
				result += sum2;
		}
		return result;
	}

	// Computes the sum of numbers contained in an odd-numbered index.
	public static double computeSumAtOdd(double[] numbers, int startIndex, int endIndex){

		double result = 0;

		if(startIndex == endIndex){
			if(startIndex % 2 != 0)
			result += numbers[startIndex];
		}
		else
		{
			int mid = (startIndex + endIndex)/2;
			double sum1 = computeSumAtOdd(numbers, startIndex, mid);
			double sum2 = computeSumAtOdd(numbers, mid+1, endIndex);
			result += (sum1 + sum2);
		}
		return result;
	}

	// Gives the count of negative numbers present in the array.
	public static int countNegative(double[] numbers, int startIndex, int endIndex){

		int result = 0;

			if(startIndex == endIndex){
				if(numbers[startIndex] < 0)
				result += 1;
			}
			else
			{
				int mid = (startIndex + endIndex)/2;
				int num1 = countNegative(numbers, startIndex, mid);
				int num2 = countNegative(numbers, mid+1, endIndex);
				if(num1 > 0)
					result += num1;
				if(num2 > 0)
					result += num2;
			}
		return result;
	}

}//end