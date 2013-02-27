// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: BankAccount is an abstract class that acts as a parent to the different account
// 				 types. All children refer to this class for thier constructors and common variables.

import java.text.*; // For NumberFormat

public abstract class BankAccount
{
	protected String accountID;
	protected double balance;
	protected double interest;

	public BankAccount(String id, double bal, double intr){

		accountID = id;
		balance = bal;
		interest = intr;

	}

	public String getAccountID(){
		return accountID;
	}

	public abstract void updateBalance();

	// toString uses the NumberFormat utility to format the balance variable to a common dollar form,
	// and the interest variable to a percentage.
	public String toString(){

		NumberFormat fbal = NumberFormat.getCurrencyInstance();
		NumberFormat fint = NumberFormat.getPercentInstance();
		String bal = fbal.format(balance);
		String intr = fint.format(interest);

		String s =  ("\nAccount ID:\t\t" + accountID + "\nBalance:\t\t" + bal + "\nInterest:\t\t" + intr + "\n");
		return s;
	}

}//end