// Assignment #: 7
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Line class creates lines from four given points, and saves
//				the color of the line specified by the user.

import java.awt.*;

public class Line{

	private int x1,x2,y1,y2;
	Color color;

	public Line(int x1, int y1, int x2, int y2, Color color){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	//Draws current line on canvas when called.
	public void draw(Graphics page){
		page.setColor(color);
		page.drawLine(x1,y1,x2,y2);
	}

}//end
