import static org.junit.Assert.*;

import org.junit.Test;


public class changeTestDUSecondPath {


	@Test
	public void Path2Change() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", -100, change.getChange());
	}
	@Test
	public void Path2Dollars() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", 0, change.getDollars());
	}
	@Test
	public void Path2Quarters() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", 0, change.getQuarters());
	}
	@Test
	public void Path2Dimes() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", 0, change.getDimes());
	}
	@Test
	public void Path2Nickels() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", 0, change.getNickels());
	}
	@Test
	public void Path2Pennies() {
		assertTrue(change.testMain(12.0,11.0));
		assertEquals("Result", 0, change.getPennies());
	}

}
