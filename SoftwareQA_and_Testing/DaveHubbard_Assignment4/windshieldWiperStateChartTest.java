import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class windshieldWiperStateChartTest {

	private static windshieldWiper testWiper;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		testWiper = new windshieldWiper(0,"OFF", 1);
	}

	@Test
	public void OFFDialUp(){
		try {
			testWiper.setWiperSpeed(0);
			testWiper.setDialPosition(1);
			testWiper.setLeverPosition("OFF");
			testWiper.senseDialUp();
			assertEquals("Result", "OFF", testWiper.getLeverPosition());
			assertEquals("Result:", 0, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void OFFLeverUp(){
		try {
			testWiper.setDialPosition(1);
			testWiper.setLeverPosition("OFF");
			testWiper.senseLeverUp();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 4, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}

	@Test
	public void INTDialUp(){
		try {
			testWiper.setDialPosition(1);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialUp();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 6, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTDialUp2(){
		try {
			testWiper.setDialPosition(2);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialUp();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 12, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTDialUp3(){
		try {
			testWiper.setDialPosition(3);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialUp();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 12, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTDialDown(){
		try {
			testWiper.setDialPosition(3);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialDown();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 6, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTDialDown2(){
		try {
			testWiper.setDialPosition(2);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialDown();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 4, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTDialDown3(){
		try {
			testWiper.setDialPosition(1);
			testWiper.setLeverPosition("INT");
			testWiper.senseDialDown();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 4, testWiper.getWiperSpeed());
		} catch (DialErrorException e) {
			fail("Dial Exception");
		}
	}
	
	@Test
	public void INTLeverUp(){
		try {
			testWiper.setLeverPosition("INT");
			testWiper.senseLeverUp();
			assertEquals("Result", "LOW", testWiper.getLeverPosition());
			assertEquals("Result:", 30, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void LOWLeverUp(){
		try {
			testWiper.setLeverPosition("LOW");
			testWiper.senseLeverUp();
			assertEquals("Result", "HIGH", testWiper.getLeverPosition());
			assertEquals("Result:", 60, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void HIGHLeverUp(){
		try {
			testWiper.setLeverPosition("HIGH");
			testWiper.senseLeverUp();
			assertEquals("Result", "HIGH", testWiper.getLeverPosition());
			assertEquals("Result:", 60, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void HIGHLeverDown(){
		try {
			testWiper.setLeverPosition("HIGH");
			testWiper.senseLeverDown();
			assertEquals("Result", "LOW", testWiper.getLeverPosition());
			assertEquals("Result:", 30, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void LOWLeverDown(){
		try {
			testWiper.setLeverPosition("LOW");
			testWiper.senseLeverDown();
			assertEquals("Result", "INT", testWiper.getLeverPosition());
			assertEquals("Result:", 4, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void INTLeverDown(){
		try {
			testWiper.setLeverPosition("INT");
			testWiper.senseLeverDown();
			assertEquals("Result", "OFF", testWiper.getLeverPosition());
			assertEquals("Result:", 0, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
	
	@Test
	public void OFFLeverDown(){
		try {
			testWiper.setLeverPosition("OFF");
			testWiper.senseLeverDown();
			assertEquals("Result", "OFF", testWiper.getLeverPosition());
			assertEquals("Result:", 0, testWiper.getWiperSpeed());
		} catch (LeverErrorException e) {
			fail("Lever Exception");
		}
	}
}