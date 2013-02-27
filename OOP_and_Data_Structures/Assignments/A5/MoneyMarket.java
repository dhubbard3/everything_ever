// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Money Market class is a child class of Bank Account. It contains the penalty
// 				 variable for applying a penalty to the balance if the balance is lower than $2000.

import java.text.*; // for NumberFormat

public class MoneyMarket extends BankAccount
{
	private double penalty;

	public MoneyMarket(String id, double bal, double intr, double pen){

		super(id,bal,intr);
		penalty = pen;
	}

	// updates balance depending on current amount. Below $2000 charges a penalty.
	public void updateBalance(){

		if (balance >= 2000){

			balance = balance + (balance* interest);

		} else {

			balance = balance - penalty;
		}

	}

	// toString uses the NumberFormat utility to format the penalty variable to a common dollar form.
	public String toString(){

			NumberFormat fpen = NumberFormat.getCurrencyInstance();
			String pen = fpen.format(penalty);

			String s = ("\nMoney Market Account:" + super.toString() + "Penalty:\t\t" + pen + "\n\n");
			return s;
	}


}//end