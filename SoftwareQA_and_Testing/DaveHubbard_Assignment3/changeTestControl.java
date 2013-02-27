import static org.junit.Assert.*;

import org.junit.Test;


public class changeTestControl {

	@Test
	public void Path1() {
		assertTrue(change.testMain(38.76,40.00));
		assertEquals("Result", 0, change.getChange());
		assertEquals("Result", 1, change.getDollars());
		assertEquals("Result", 0, change.getQuarters());
		assertEquals("Result", 2, change.getDimes());
		assertEquals("Result", 0, change.getNickels());
		assertEquals("Result", 4, change.getPennies());
	}
	
	@Test
	public void Path2() {
		assertTrue(change.testMain(0.0,0.0));
		assertEquals("Result", 0, change.getChange());
		assertEquals("Result", 0, change.getDollars());
		assertEquals("Result", 0, change.getQuarters());
		assertEquals("Result", 0, change.getDimes());
		assertEquals("Result", 0, change.getNickels());
		assertEquals("Result", 0, change.getPennies());
	}

}
