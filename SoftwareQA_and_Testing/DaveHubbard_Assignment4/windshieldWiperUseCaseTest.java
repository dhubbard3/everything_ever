import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class windshieldWiperUseCaseTest {
	
	private static windshieldWiper testWiper;

	@BeforeClass
	public static void setUpBeforeClass(){
		testWiper = new windshieldWiper(0,"OFF", 1);
	}

	@Test
	public void userTest() {
		try {
		testWiper.senseLeverUp();//OFF to INT
		testWiper.senseLeverUp();// INT to LOW
		assertEquals("Result:", 30, testWiper.getWiperSpeed());
		testWiper.senseLeverUp();
		assertEquals("Result:", "HIGH", testWiper.getLeverPosition());
		assertEquals("Result:", 60, testWiper.getWiperSpeed());
		testWiper.setDialPosition(3);
		assertEquals("Result:", "HIGH", testWiper.getLeverPosition());
		assertEquals("Result:", 60, testWiper.getWiperSpeed());
		testWiper.senseLeverDown();// HIGH to LOW
		testWiper.senseLeverDown();// LOW to INT
		assertEquals("Result:", "INT", testWiper.getLeverPosition());
		assertEquals("Result:", 12, testWiper.getWiperSpeed());
		testWiper.senseLeverDown();
		assertEquals("Result:", "OFF", testWiper.getLeverPosition());
		assertEquals("Result:", 0, testWiper.getWiperSpeed());
		
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
		
	}

}
