/*///////////////////////////////////////////////////////////////// Lab #16 //
Dave Hubbard	04/14/09
CSC110			Rectangle Class

This class creates a rectangle object that can be compared to other objects
using various methods.
////////////////////////////////////////////////////////////////////////////*/

import java.awt.Point;

public class Rectangle{

	private int x;
	private int y;
	private int width;
	private int height;

	public Rectangle(int nx,int ny,int nwidth,int nheight){
		x = nx;
		y = ny;
		width = nwidth;
		height = nheight;
	}

	public Rectangle(Point p,int nwidth,int nheight){
		x = p.x;
		y = p.y;
		width = nwidth;
		height = nheight;
	}

	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public String toString(){
		return "["+x+","+y+","+width+","+height+"]";
	}

	public boolean contains(int x, int y){
		if(x > this.x && x < this.x+width){
			return (y > this.y && y < this.y+height);
		}
		return false;
	}

	public boolean contains(Point p){
			if(p.x > x && p.x < x+width){
				return (p.y > y && p.y < y+height);
			}
			return false;
	}

	public boolean equals(Object o){
		if(o instanceof Rectangle){
			Rectangle other = (Rectangle) o;
			if( x==other.getX() && y==other.getY()){
				return width==other.getWidth() && height==other.getHeight();
			}
		}
		return false;
	}

	public Rectangle union(Rectangle rect){
		int rX= rect.getX();
		int rY= rect.getY();
		int rW= rect.getWidth();
		int rH= rect.getHeight();
		int newX= 0;
		int newY= 0;
		int newW= 0;
		int newH= 0;

		if(x>rX)
			newX = rX;
		else
			newX = x;

		if(y>rY)
			newY = rY;
		else
			newY = y;

		if(x+width>rX+rW)
			newW =(x+width)-rX;
		else
			newW =(rX+rW)-x;

		if(y+height>rY+rH)
			newH =(height+y)-rY;
		else
			newH=(rY+rH)-y;

		return new Rectangle(newX,newY,newW,newH);
	}

	public Rectangle intersection(Rectangle rect){
		int rX= rect.getX();
		int rY= rect.getY();
		int rW= rect.getWidth();
		int rH= rect.getHeight();
		int newX= 0;
		int newY= 0;
		int newW= 0;
		int newH= 0;

		if(rX+rW<=x || rX>=x+width)
			return new Rectangle(0,0,0,0);
		if(rY+rH<=y || rY>=y+height)
			return new Rectangle(0,0,0,0);

		if(x>rX){
			newX = x;
			newW =(rX+rW)-x;
		}else{
			newX= rX;
			newW=(x+width)-rX;
		}

		if(y>rY){
			newY = y;
			newH =(rY+rH)-y;
		}else{
			newY= rY;
			newH=(y+height)-rY;
		}
		return new Rectangle(newX,newY,newW,newH);
	}

	public static void main(String[] args){
		Rectangle rec = new Rectangle(3,4,4,3);
		Rectangle tan = new Rectangle(0,5,4,3);
		Point p = new Point(5,6);
		Rectangle gle = new Rectangle(p,9,22);
		System.out.println("R1 = "+rec.toString());
		System.out.println("R2 = "+tan.toString());
		System.out.println("R3 = "+gle.toString());
		System.out.println("R1 = R2? : "+rec.equals(tan));
		System.out.println("R2 = R3? : "+tan.equals(gle));
		System.out.println("R1 contains ("+p.x+","+p.y+"): "+rec.contains(p));
		System.out.println("R2 contains (20,22): "+ tan.contains(20,22));
		System.out.print("Intersect(1&2): ");
		System.out.println((rec.intersection(tan)).toString());
		System.out.print("Union(2&3): ");
		System.out.println((tan.union(gle)).toString());
	}

}//end