// Assignment #: 8
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The BankSystem class contains all of the methods called by the Assignment
// 				8 class. The customer and bank lists are stored and manipulated in various
//				ways, such as sorting and adding customers.

import java.util.*;
import java.io.*;

public class BankSystem implements Serializable{

	private ArrayList customerList;
	private ArrayList bankList;


	public BankSystem(){

		customerList = new ArrayList();
		bankList = new ArrayList();
	}

	// Checks if a given customer ID matches the instance.
	public int customerExists(String customer){
		String id;
		Customer c;
		for(int i = 0; i < customerList.size(); i++){
			c = (Customer)customerList.get(i);
			id = c.getCustomerID();
			if (customer.equals(id))
			return i;
		}
		return -1;
	}

	// Adds a customer if they do not already exist.
	public boolean addCustomer(String customer){
		Customer toAdd = CustomerParser.parseStringToCustomer(customer);
		String id = toAdd.getCustomerID();
		if(customerExists(id) > -1){
			return false;
		}
		else
		{
			customerList.add(toAdd);
			return true;
		}
	}

	// Removes a customer from the list if they exist.
	public boolean removeCustomer(String customer){
		int toRemove = customerExists(customer);
		if(toRemove > -1){
			customerList.remove(toRemove);
			return true;
		}
		else
		{
			return false;
		}
	}

	// Sorts the customer list.
	public void sortCustomers(){
		Sorts.sort(customerList);
	}

	// Returns a string containing all customer information.
	public String listCustomers(){
		String info;
		String result = "";

		if(customerList.isEmpty()){
			return "no customer\n";
		}
		else
		{
			for(int i = 0; i < customerList.size(); i++){
				Customer temp = (Customer)customerList.get(i);
				info = temp.toString();
				result += info;
			}
		}
		return result;
	}

	// Checks four parameters to conclude if a Bank is in the bank list.
	public int bankExists(String name, int id, String city, String state){
		Bank b;

		for(int i = 0; i < bankList.size(); i++){
			b = (Bank)bankList.get(i);
			if(name.equals(b.getBankName())){
				if(id == (b.getBankID())){
					if(city.equals(b.getBankAddress().getCity())){
						if(state.equals(b.getBankAddress().getState())){
							return i;
						}
					}
				}
			}
		}
		return -1;
	}

	// Adds a bank if it does not already exist.
	public boolean addBank(String name){
		Bank b = BankParser.parseStringToBank(name);
		Address a = b.getBankAddress();
		if(bankExists(b.getBankName(),b.getBankID(),a.getCity(),a.getState()) > -1){
			return false;
		}
		else
		{
			bankList.add(b);
			return true;
		}
	}

	// Removes a bank if on the list.
	public boolean removeBank(String name, int id, String city, String state){
		int i = bankExists(name,id,city,state);
		if(i > -1){
			bankList.remove(i);
			return true;
		}
		else
		{
			return false;
		}
	}

	// Sorts the bank list lexographically.
	public void sortBanks(){
		Sorts.sort(bankList);
	}

	// Returns a list of all banks.
	public String listBanks(){
		String info;
		String result = "";

		if(bankList.isEmpty()){
			return "no bank\n";
		}
		else
		{
			for(int i = 0; i < bankList.size(); i++){
				Bank temp = (Bank)bankList.get(i);
				info = temp.toString();
				result += info;
			}
		}
		return result;
	}

	// Deletes all customers and banks from the Bank System.
	public void closeBankSystem(){
		customerList.clear();
		bankList.clear();
	}

}//end
