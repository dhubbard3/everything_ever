import org.junit.Test;


public class TriangleDTTest {

	@Test
	public void testIsNotTriangle() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(8,2,5), "Not a triangle");
	}
	
	@Test
	public void testIsNotTriangle2() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(1,17,12), "Not a triangle");
	}
	
	@Test
	public void testIsNotTriangle3() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(1,2,3), "Not a triangle");
	}
	
	@Test
	public void testIsEquilateral() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(146,146,146), "Equilateral");
	}
	
	@Test
	public void testIsScalene() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(34,15,27), "Scalene");
	}
	
	@Test
	public void testIsIsoceles() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(36,36,45), "Isosceles");
	}
	
	@Test
	public void testIsIsoceles2() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(99,78,78), "Isosceles");
	}
	
	@Test
	public void testIsIsoceles3() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(156,122,156), "Isosceles");
	}
	
	@Test
	public void testIsRight() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(3,4,5), "Right");
	}
	
	@Test
	public void testIsRight2() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(41,9,40), "Right");
	}
	
	@Test
	public void testIsRight3() {
		Triangle t = new Triangle();
		org.junit.Assert.assertEquals(t.Triangle_Test(12,13,5), "Right");
	}

}
