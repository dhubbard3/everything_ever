// Assignment #: 4
// 		   Name: Dave Hubbard
// 	  StudentID: 1202945271
// 		Lecture: 3
//  Description: Assignment 4 class displays a menu of choices to a user
//               and performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is entered.

import java.io.*;
import java.util.*;

public class Assignment4
 {
  public static void main (String[] args)
   {
       // local variables, can be accessed anywhere from the main method
       char input1 = 'Z';
       String inputInfo;
       String bankName;
       int bankID;
       String city;
       String state;
       String line = new String();

       // instantiate a Bank object
       Bank bank1 = new Bank();

       printMenu();

       //Create a Scanner object to read user input
       Scanner scan = new Scanner(System.in);


       do  // will ask for user input
        {
         System.out.println("What action would you like to perform?");
         line = scan.nextLine();

         if (line.length() == 1)
          {
           input1 = line.charAt(0);
           input1 = Character.toUpperCase(input1);

           // matches one of the case statement
           switch (input1)
            {
             case 'A':   //Add Bank
               System.out.print("Please enter the bank information:\n");
               System.out.print("Enter a bank name:\n");
               bankName = scan.nextLine();
               bank1.setBankName(bankName);

               System.out.print("Enter a bank ID:\n");
               bankID = Integer.parseInt(scan.nextLine());
               bank1.setBankID(bankID);

               System.out.print("Enter a city of the bank:\n");
               city = scan.nextLine();
               System.out.print("Enter a state of the bank:\n");
               state = scan.nextLine();
               bank1.setBankAddress(city, state);
               break;
             case 'D':   //Display bank
               System.out.print(bank1);
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
        } while (input1 != 'Q' || line.length() != 1);
   }


  /** The method printMenu displays the menu to a user**/
  public static void printMenu()
   {
     System.out.print("Choice\t\tAction\n" +
                        "------\t\t------\n" +
                        "A\t\tAdd Bank\n" +
                        "D\t\tDisplay Bank\n" +
                        "Q\t\tQuit\n" +
                        "?\t\tDisplay Help\n\n");
  }
}

