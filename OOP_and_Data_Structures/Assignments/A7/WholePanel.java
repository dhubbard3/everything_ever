// Assignment #: 7
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The WholePanel class organizes all objects within the applet. It keeps
//				track of the currently selected color, handles all listeners for the buttons
//				and mouse, and manages the paint component for drawing.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class WholePanel extends JPanel
{
   private Color currentColor;
   private CanvasPanel canvas;
   private JPanel leftPanel;
   private JButton undo, erase;
   private ArrayList lineList, lineCopy;
   private int x1,y1,x2,y2,x3,y3;
   private JRadioButton black, red, blue, green, orange;
   private String prev="";


   public WholePanel()
   {
      //default color to draw is black
      currentColor = Color.black;
      lineList = new ArrayList();
      lineCopy = new ArrayList();

      undo = new JButton ("Undo");
      erase = new JButton ("Erase");
      black = new JRadioButton("black",true);
      red   = new JRadioButton("red");
      blue  = new JRadioButton("blue");
      green = new JRadioButton("green");
      orange = new JRadioButton("orange");

      ButtonGroup group = new ButtonGroup();
      group.add(black);
      group.add(red);
      group.add(blue);
      group.add(green);
      group.add(orange);

      leftPanel = new JPanel();
      leftPanel.setLayout(new GridLayout(7,1));
      leftPanel.add(black);
      leftPanel.add(red);
      leftPanel.add(blue);
      leftPanel.add(green);
      leftPanel.add(orange);
      leftPanel.add(undo);
      leftPanel.add(erase);

      canvas = new CanvasPanel();

      JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, canvas);

      setLayout(new BorderLayout());
      add(sp);

	  ButtonListener blis = new ButtonListener();
	  undo.addActionListener(blis);
	  erase.addActionListener(blis);

	  ColorListener clis = new ColorListener();
	  black.addActionListener(clis);
	  red.addActionListener(clis);
	  blue.addActionListener(clis);
	  green.addActionListener(clis);
	  orange.addActionListener(clis);
    }

  //CanvasPanel is the panel where lines will be drawn
  private class CanvasPanel extends JPanel
   {
      public CanvasPanel()
      {
		PointListener lis = new PointListener();
	  	this.addMouseListener(lis);
      	this.addMouseMotionListener(lis);
	  }

      //this method draws all lines specified by a user
      public void paintComponent(Graphics page)
       {
        super.paintComponent(page);
        setBackground(Color.WHITE);
        page.setColor(currentColor);

		page.drawLine(x1,y1,x2,y2);

		for(int i=0; i < lineList.size(); i++){
			Line l = (Line)lineList.get(i);
			l.draw(page);
		}

       }
    } //end of CanvasPanel class


   //ButtonListener defined actions to take in case
   //"Undo", or "Erase" is chosed.
   private class ButtonListener implements ActionListener
    {
      public void actionPerformed (ActionEvent event)
      {
         String command = event.getActionCommand();

		 if(command.equals("Undo")){

			if(lineList.size()>0){
				int last = (lineList.size()-1);
				lineList.remove(last);
				repaint();
			}
			else
			{

				if(lineList.isEmpty() && prev.equals("Erase")){
					lineList = (ArrayList)lineCopy.clone();
					repaint();
				}
			}
		}
		else
		{
			if (lineList.isEmpty() == false){
				lineCopy = (ArrayList)lineList.clone();	//creates a clone of A.list
				lineList.clear();
				repaint();
			}
		}

		prev = command; //This string keeps track of the previous button pressed.
     }
   } // end of ButtonListener


   // listener class to set the color chosen by a user using
   // the color radio buttons
   private class ColorListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
         {
           Object choice = event.getSource();

           if(choice == black)
           	 currentColor = Color.BLACK;
           if(choice == red)
           	 currentColor = Color.RED;
           if(choice == blue)
           	 currentColor = Color.BLUE;
           if(choice == green)
           	 currentColor = Color.GREEN;
           if(choice == orange)
          	 currentColor = Color.ORANGE;
         }
    } // end of ColorListener


   // listener class that listens to the mouse
   public class PointListener implements MouseListener, MouseMotionListener
    {
		Point p1,p2,p3;

     public void mousePressed (MouseEvent event)
      {
          p1 = event.getPoint();
          x1 = p1.x;
          y1 = p1.y;
      }

	 //Creates a new line based on starting and final points.
     public void mouseReleased (MouseEvent event)
      {
          p3 = event.getPoint();
          x3 = p3.x;
          y3 = p3.y;
          Line line = new Line(p1.x,p1.y,p3.x,p3.y,currentColor);
          lineList.add(line);
          x1=0;
          x2=0;
          y1=0;
          y2=0;
      }

	 //Gives coordinates for the current line
     public void mouseDragged(MouseEvent event)
      {
          p2 = event.getPoint();
          x2 = p2.x;
          y2 = p2.y;
		  repaint();
      }

     public void mouseClicked (MouseEvent event) {}
     public void mouseEntered (MouseEvent event) {}
     public void mouseExited (MouseEvent event) {}
     public void mouseMoved(MouseEvent event) {}

    } // end of PointListener

} // end of Whole Panel Class

