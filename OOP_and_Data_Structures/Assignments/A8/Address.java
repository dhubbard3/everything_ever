// Assignment #: 8
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Address class stores the city and state of a bank.

import java.io.*;

public class Address implements Comparable, Serializable
{
	private String city;
	private String state;

	public Address(){

		city  = "?";
		state = "?";
	}

	// Returns current city name.
	public String getCity(){
		return city;
	}

	// Returns current state name
	public String getState(){
		return state;
	}

	// Sets city name.
	public void setCity(String city){
		this.city = city;
	}

	// Sets the state bank is located in.
	public void setState(String state){
		this.state = state;
	}

	// Compares the Address instance with another for sorting purposes.
	public int compareTo(Object o){
		int result;
			if(state.equals(((Address)o).state)){

				result = (city.compareTo(((Address)o).city)); //Compares cities if states are equal

			}else{

				result = (state.compareTo(((Address)o).state));
			}
	    return result;
	}


	// Returns address information.
	public String toString(){
		return city + "," + state;
	}

} //end