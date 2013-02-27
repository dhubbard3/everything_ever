// Assignment #: 8
//         Name: Dave Hubbard
//    StudentID: 1202945271
//  Lab Lecture: 3
//  Description: The Customer class describes a customer entity and contains
//  attributes first name, last name, customer ID, and cash amount.
//  It also contains the accessors and the modifiers of each attribute.

import java.text.*;
import java.io.*;

public class Customer implements Serializable, Comparable
 {
  private String firstName;
  private String lastName;
  private String customerID;
  private double cashAmount;

  // constructor method to initialize each instance variable.
  public Customer()
   {
    firstName = new String("?");
    lastName = new String("?");
    customerID = new String("?");
    cashAmount = 0.0;
   }

  // The next four methods are accessor method for
  // each instance variable.
  public String getFirstName()
   {
    return firstName;
   }

  public String getLastName()
   {
    return lastName;
   }

  public String getCustomerID()
   {
    return customerID;
   }

  public double getCashAmount()
   {
    return cashAmount;
   }

  // The next four methods are mutator methods for
  // each instance variable.
  public void setFirstName(String fname)
   {
    firstName = fname;
   }

  public void setLastName(String lname)
   {
    lastName = lname;
   }

  public void setCustomerID(String custId)
   {
     customerID = custId;
   }

  public void setCashAmount(double cash)
   {
    cashAmount = cash;
   }

  public int compareTo(Object o)
  {
	  int result;
	  if(lastName.equals(((Customer)o).lastName)){

		result = (firstName.compareTo(((Customer)o).firstName));

	  }else{

		result = (lastName.compareTo(((Customer)o).lastName));
	  }
	  return result;
   }

  // The toString method returns a string describing
  // the value of each instance variable.
  public String toString()
   {
    String customerString;

    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    customerString = "\nFirst name:\t\t" + firstName + "\n"
                   + "Last name:\t\t" + lastName + "\n"
                   + "Customer ID:\t\t" + customerID + "\n"
                   + "Cash amount:\t\t" + fmt.format(cashAmount)
                   + "\n\n";

    return customerString;
   }

} // end of Customer class


