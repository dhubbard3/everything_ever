import org.junit.Test;


public class TriangleEPTest {

	@Test
	public void testIsNotTriangle() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(8,2,5), "Not a triangle");
	}
	
	@Test
	public void testIsEquilateral() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(156,156,156), "Equilateral");
	}
	
	@Test
	public void testIsScalene() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(3,4,6), "Scalene");
	}
	
	@Test
	public void testIsIsoceles() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(96,95,96), "Isosceles");
	}
	
	@Test
	public void testIsRight() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(4,5,3), "Right");
	}
	
	@Test
	public void testIsOutOfRange() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(33,68,201), "value of c is out of range");
	}
}

