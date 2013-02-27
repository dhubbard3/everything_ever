public class Triangle {
	
	
	public String Triangle_Test(int a, int b, int c)
	{
		final int MIN = 1;
		final int MAX = 200;

		if(a < MIN || a > MAX)
			return "value of a is out of range"; 
		if(b < MIN || b > MAX)
			return "value of b is out of range"; 
		if(c < MIN || c > MAX)
			return "value of c is out of range"; 

		if(a < b+c && b < a+c && c < a+b) { // if triangle
			if(a == b && b == c)
				return "Equilateral";
			else if ((a*a == (b*b + c*c)) || (b*b == (a*a + c*c)) || (c*c == (a*a + b*b)))
				return "Right";
			else if(a != b && a != c && b != c)
				return "Scalene";
			else	
					return "Isosceles";
			
		} else
			    return "Not a triangle";
			
	}
	

}