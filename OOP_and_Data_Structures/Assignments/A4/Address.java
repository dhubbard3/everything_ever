// Assignment #: 4
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Address class stores the city and state of a bank.


public class Address
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

	// Returns address information.
	public String toString(){
		return city + "," + state;
	}

} //end