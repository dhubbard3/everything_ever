// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The CheckingAccount class keeps track of the number of checks used with the account.
// 				 This info is applied to the balance update to determine if the account receives
// 				 more money.

public class Checking extends BankAccount
{

	private int numberOfUsedChecks;

	public Checking(String id, double bal, double intr, int chk){

		super(id,bal,intr);
		numberOfUsedChecks = chk;
	}

	// If the number of checks exceeds 30 or the balance is less than $1000, the account will not update.
	public void updateBalance(){

		if (balance >= 1000 && numberOfUsedChecks < 30){
			balance = balance + (balance * interest);
		}

	}

	public String toString(){

		String s = ("\nChecking Account:" + super.toString() + "Number of Used Checks:\t" + numberOfUsedChecks + "\n\n");
		return s;
	}


}//end