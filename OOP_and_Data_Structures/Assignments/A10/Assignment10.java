// Assignment #: 10
//         Name: Dave Hubbard
//    StudentID: 1202945271
//  Lab Lecture: 3
//  Description: The Assignment 10 class displays a menu of choices to a user
//               and performs the chosen task. It will keep asking a user to
//               enter the next choice until the choice of 'Q' (Quit) is
//               entered.

import java.io.*;

public class Assignment10
{
   public static void main(String[] args)
   {
      char input1;
       String inputInfo = new String();
       int operation2;
       String line = new String();

       //create a linked list to be used in this method.
       LinkedList list1 = new LinkedList();

       try
        {
         // print out the menu
         printMenu();

         // create a BufferedReader object to read input from a keyboard
         InputStreamReader isr = new InputStreamReader (System.in);
         BufferedReader stdin = new BufferedReader (isr);

         do
          {
           System.out.print("What action would you like to perform?\n");
           line = stdin.readLine().trim();  //read a line
           input1 = line.charAt(0);
           input1 = Character.toUpperCase(input1);

           if (line.length() == 1)   //check if a user entered only one character
            {
             switch (input1)
              {
               case 'A':   //Add String
                 System.out.print("Please enter a string to add:\n");
                 String str1 = stdin.readLine().trim();
                 System.out.print("Please enter an index to add:\n");
                 inputInfo = stdin.readLine().trim();
                 int addIndex = Integer.parseInt(inputInfo);
                 list1.addElement(addIndex, str1);
                 break;
               case 'D':   //Duplicate each element
                 list1.duplicateEach();
                 System.out.print("Each element has been duplicated.\n");
                 break;
               case 'E':   //Search for a String at an Index
                 System.out.print("Please enter an index to search:\n");
                 inputInfo = stdin.readLine().trim();
                 int searchIndex = Integer.parseInt(inputInfo);
                 System.out.print("String at the index " + searchIndex + " is "
                                + list1.getElement(searchIndex) + "\n");
                 break;
               case 'F':   //Find the lexicographically largest string in the linked list
                 Object large = list1.findLargest();
                 if (large == null)
                   System.out.print("This linked list is empty./n");
                 else
                   System.out.print("The largest string is: " + large + "\n");
                 break;
               case 'L':   //List Strings
                 System.out.print(list1.toString());
                 break;
               case 'O':  // List Current Size
                 System.out.print("The current size is " + list1.size() + "\n");
                 break;
               case 'Q':   //Quit
                 break;
               case 'R':  //Remove First Few
                 System.out.print("Please enter a number of elements to be removed from the beginning of the linked list:\n");
                 inputInfo = stdin.readLine().trim();
                 int howMany = Integer.parseInt(inputInfo);
                 list1.removeFirstFew(howMany);
                 break;
               case 'S':  //Search and Remove
                 System.out.print("Please enter a string to be searched and removed:\n");
                 inputInfo = stdin.readLine().trim();
                 int index = list1.searchAndRemove(inputInfo);
                 if (index == -1)
                  System.out.print("The string was not found.\n");
                 else
                  System.out.print("The string " + inputInfo + " at the index " + index + " was removed.\n");
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
       catch (IOException exception)
        {
          System.out.print("IO Exception\n");
        }
    }

    /** The method printMenu displays the menu to a user **/
    public static void printMenu()
     {
       System.out.print("Choice\t\tAction\n" +
                        "------\t\t------\n" +
                        "A\t\tAdd String\n" +
                        "D\t\tDuplicate Each\n" +
                        "E\t\tSearch for String\n" +
                        "F\t\tFind the Largest\n" +
                        "L\t\tList Strings\n" +
                        "O\t\tList Current Size\n" +
                        "Q\t\tQuit\n" +
                        "R\t\tRemove First Few\n" +
                        "S\t\tSearch and Remove\n" +
                        "?\t\tDisplay Help\n\n");
    } //end of printMenu()
}
