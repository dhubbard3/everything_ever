/*////////////////////////////////////////////////

Dave Hubbard 	03/11/09
CSC110			Lab09

This program computes taxes based on filing status
and income.

/////////////////////////////////////////////////*/



import java.util.Scanner;

public class Lab09{
	public static void main(String[] args){

		System.out.print("Please enter your filing status (Single or Married): ");
		Scanner console = new Scanner(System.in);
		String status = console.nextLine();
		String status2 = status.toLowerCase();
		char stat = status2.charAt(0);
		System.out.print("Please enter your amount of income: $");
		double inc = console.nextDouble();
		System.out.println();
		System.out.printf("The amount of tax owed is: $%.2f\n", computeTax(inc, stat));
	}

	// Computes the amount of tax owed using status and income //
	public static double computeTax(double income, char status){
		double tax = 0.0;

		if (status == 's'){
			if (income >= 0 && income < 7550)
				tax = income*.1;
			else if (income >= 7550 && income < 30650)
				tax = 755.00 + income*.15;
			else if (income >= 30650 && income < 74200)
				tax = 4220.00 + income*.25;
			else if (income >= 74200 && income < 154800)
				tax = 15107.50 + income*.28;
			else if (income >= 154800 && income < 336550)
				tax = 37675.50 + income*.33;
			else if(income >= 336550)
				tax = 97653.00 + income*.35;

		} else if (status == 'm'){
			if (income >= 0 && income < 15100)
				tax = income*.1;
			else if (income >= 15100 && income < 61300)
				tax = 1510.00 + income*.15;
			else if (income >= 61300 && income < 123700)
				tax = 8440.00 + income*.25;
			else if (income >= 123700 && income < 188450)
				tax = 24040.00 + income*.28;
			else if (income >= 188450 && income < 336550)
				tax = 42170.00 + income*.33;
			else if(income >= 336550)
				tax = 91043.00 + income*.35;
		} else {
			System.out.println("You did not enter a valid filing status.");
		}

		return tax;
	}
}