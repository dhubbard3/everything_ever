// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The SavingAccount class is pretty straightforward. It keeps track of various variables and
//				 updates its balance based on interest rate.

public class Saving extends BankAccount
{
	public Saving(String id, double bal, double intr){

		super(id,bal,intr);
	}

	public void updateBalance(){

		balance = balance + (balance * interest);
	}

	public String toString(){

		String s = ("\nSaving Account:" + super.toString() + "\n");
		return s;
	}

}//end