// Assignment #: 12
//         Name: your name
//    StudentID: your id
//      Lecture: your lecture
//  Description: it needs to be filled.


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel
 {
   private BallPanel ball1, ball2;
   private int width, height;
   private int DIAMETER = 30;

  public ControlPanel(int width, int height)
   {
       this.width = width;
       this.height = height;

       int buttonH = 10; // approximate height of buttons

       //create two ball panels, one with red (background cyan), one with blue (background yellow)
       ball1 = new BallPanel(0, (height-buttonH)/4-DIAMETER/2, Color.red, Color.cyan);
       ball2 = new BallPanel(0, (height-buttonH)/4-DIAMETER/2, Color.blue, Color.yellow);

       /***to be completed***/


       //set preferred size of this panel

       setPreferredSize(new Dimension(width,height));
    }


  private class ButtonListener implements ActionListener
   {
       public void actionPerformed(ActionEvent event)
        {
            Object action = event.getSource();

            /***to be completed***/

         }
     } //end of ButtonListener

   private class SliderListener implements ChangeListener
    {
        public void stateChanged(ChangeEvent event)
         {
            /***to be completed***/
         }

     } //end of SliderListener

} //end of ControlPanel
