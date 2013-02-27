// Assignment #: 5
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Assignment 5 class displays a menu of choices
//               (add saving, checking, or money market accounts,
//               update their balance, search a bank account, list accounts,
//               quit, display menu) to a user.
//               Then it performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is
//               entered.

import java.io.*;         //to use InputStreamReader and BufferedReader
import java.util.*;       //to use ArrayList

public class Assignment5
 {
  public static void main (String[] args)
   {
     char input1;
     String inputInfo = new String();
     String line = new String();
     boolean found = false;

     // ArrayList object is used to store member objects
     ArrayList accountList = new ArrayList();

     try
      {
       printMenu();     // print out menu

       // create a BufferedReader object to read input from a keyboard
       InputStreamReader isr = new InputStreamReader (System.in);
       BufferedReader stdin = new BufferedReader (isr);

       do
        {
         System.out.println("What action would you like to perform?");
         line = stdin.readLine().trim();
         input1 = line.charAt(0);
         input1 = Character.toUpperCase(input1);

         if (line.length() == 1)
          {
           switch (input1)
            {
             case 'A':   //Add Account
               System.out.print("Please enter an account information to add:\n");
               inputInfo = stdin.readLine().trim();
			   BankAccount ba = BankAccountParser.parseStringToAccount(inputInfo);
			   accountList.add(ba);
               break;
             case 'B':   //Update Balance
			    for( int i = 0; i < accountList.size() ; i++){
					Object obj = accountList.get(i);
					BankAccount b = (BankAccount)obj; //converts Object to BankAccount type.
					b.updateBalance();
				}
                System.out.print("balance updated\n");
               break;
             case 'D':   //Search for Account
               System.out.print("Please enter an accountID to search:\n");
               inputInfo = stdin.readLine().trim();

  				for( int i = 0; i < accountList.size() ; i++){
					Object obj = accountList.get(i);
					BankAccount b = (BankAccount)obj;

					if (b.getAccountID().equals(inputInfo))
						found = true;
				}

                if (found == true)
                 System.out.print("account found\n");
                else
                 System.out.print("account not found\n");

                 found = false;
               break;
              case 'L':   //List Accounts

				if (accountList.isEmpty()){
					System.out.println ( "no account\n");
				} else {
					  for( int i = 0; i < accountList.size() ; i++){
						Object obj = accountList.get(i);
						BankAccount b = (BankAccount)obj;
						System.out.print(b.toString());
					}
				}
               break;
             case 'Q':   //Quit
               break;
             case '?':   //Display Menu
               printMenu();
               break;
             default:
               System.out.print("Unknown action\n");
               break;
            }
         }
        else
         {
           System.out.print("Unknown action\n");
          }
        } while (input1 != 'Q'); // stop the loop when Q is read
      }
     catch (IOException exception)
      {
        System.out.println("IO Exception");
      }
  }

  /** The method printMenu displays the menu to a user **/
  public static void printMenu()
   {
     System.out.print("Choice\t\tAction\n" +
                      "------\t\t------\n" +
                      "A\t\tAdd Account\n" +
                      "B\t\tUpdate Balance\n" +
                      "D\t\tSearch for Account\n" +
                      "L\t\tList Accounts\n" +
                      "Q\t\tQuit\n" +
                      "?\t\tDisplay Help\n\n");
  }
}


