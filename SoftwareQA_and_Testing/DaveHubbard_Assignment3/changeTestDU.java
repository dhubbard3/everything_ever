import static org.junit.Assert.*;

import org.junit.Test;


public class changeTestDU {
	
// Tests Path 1
	
	@Test
	public void Path1Change() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 0, change.getChange());
	}
	@Test
	public void Path1Dollars() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 57, change.getDollars());
	}
	@Test
	public void Path1Quarters() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 1, change.getQuarters());
	}
	@Test
	public void Path1Dimes() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 1, change.getDimes());
	}
	@Test
	public void Path1Nickels() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 1, change.getNickels());
	}
	@Test
	public void Path1Pennies() {
		assertTrue(change.testMain(243.81, 301.25));
		assertEquals("Result", 4, change.getPennies());
	}
	
}
