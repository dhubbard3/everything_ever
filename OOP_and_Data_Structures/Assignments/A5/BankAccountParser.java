// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The BankAccountParser class accepts a string, sets a delimiter, and parses the information
// 				 given to use for the creation of specific types of bank accounts. For now, this class returns
//				 a generic Savings account if the information given is not formatted correctly.

import java.util.*; // for Scanner

public class BankAccountParser
{

	public static BankAccount parseStringToAccount(String lineToParse)
	{

		String line = lineToParse;
		Scanner s = new Scanner(line).useDelimiter("/");

		String type = s.next();

		if (type.equals("Saving")){
			String id = s.next();
			double bal = s.nextDouble();
			double intr = s.nextDouble();

			Saving sa = new Saving(id,bal,intr);

			return sa;
		}

		if (type.equals("Checking")){
			String id = s.next();
			double bal = s.nextDouble();
			double intr = s.nextDouble();
			int checks = s.nextInt();

			Checking ca = new Checking(id,bal,intr,checks);

			return ca;
		}

		if (type.equals("MoneyMarket")){
			String id = s.next();
			double bal = s.nextDouble();
			double intr = s.nextDouble();
			double pen = s.nextDouble();

			MoneyMarket mma = new MoneyMarket(id,bal,intr,pen);

			return mma;
		}

		// In case the information given is not formatted correctly, this account is returned.
		Saving dummy = new Saving("???",0,0);
		return dummy;
	}

}//end