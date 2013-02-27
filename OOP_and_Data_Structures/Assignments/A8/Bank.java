// Assignment #: 8
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Bank class contains information on the bank name and id.

import java.io.*;

public class Bank implements Comparable, Serializable
{
	private String  bankName;
	private int     bankID;
	private Address bankAddress;

	public Bank(){

		bankName = "?";
		bankID   = 0;
		bankAddress = new Address(); //create new Address instance.
	}

	// Returns current bank name.
	public String getBankName(){
		return bankName;
	}

	// Returns current bank ID number.
	public int getBankID(){
		return bankID;
	}

	// Returns current bank address.
	public Address getBankAddress(){
		return bankAddress;
	}

	// Sets bank name.
	public void setBankName(String name){
		bankName = name;
	}

	// Sets the bank ID number.
	public void setBankID(int id){
		bankID = id;
	}

	// Takes two strings and sets the bank address.
	public void setBankAddress(String city, String state){

		bankAddress.setCity(city);
		bankAddress.setState(state);
	}

	// Compares the current Bank instance with another for sorting.
	public int compareTo(Object o){
		int result;
		  if(bankName.equals(((Bank)o).getBankName())){

			if(bankID ==(((Bank)o).getBankID())){ //Compares Addresses name and ID are equal
				result = (bankAddress.compareTo(((Bank)o).getBankAddress()));
			}
			else{

				if(bankID > (((Bank)o).getBankID()))
					return 1;
				else
					return -1;
			}

		  }else{

			result = (bankName.compareTo(((Bank)o).getBankName()));
		  }
	    return result;
	}

	// Returns all bank information.
	public String toString(){

		return ("\nBank name:\t\t"+ bankName +"\nBank ID:\t\t"+ bankID +"\nBank address:\t\t" + bankAddress +"\n\n");
	}

} //end
