// Tests and shows how to use the DisplayFrame class
// You should only need to copy DisplayFrame.java and GridPanel.java
// into your working folder.  Then use the DisplayFrame as done in
// main below.
import java.util.*;

public class GUITest {

    //***************************************************************************
    // shows how to create and use a DisplayFrame
    // This main acts like a client similar to the way your program would be a client
    // of the DisplayFrame class
    ///////////////////////////////////////////////////////////////
    public static void main(String[] arg) throws Exception {
        Random gen = new Random();
        final int X = 20;
        int watchRow = 0;
        int watchCol = 0;
        char c;
        byte[] b = new byte[10];

        // This is how you create your DisplayFrame for use
        // create a 20 X 20 DisplayFrame with the String title
        // Notice that this size is the same as your 2-dim array of Organisms
        DisplayFrame disp = new DisplayFrame("My DisplayFrame", X, X );

        // Your application will set up a 2-dim. array of Organisms
        // When you are ready to display it, create a 2-dim array of char
        // that contains the display features of your grid: x's, o's, blanks, etc.
        char[][] data = new char[X][X];

        for(int i = 0; i < X; i++)
            for(int j = 0; j < X; j++)
                data[i][j] = ' ';  // setting every cell to a space

        // To use the DisplayFrame, pass the setGrid method the 2-dim array of char
        // that contains the display features of your Oganism grid
        disp.setGrid( data );

        System.out.println("You should now be able to see the DisplayFrame of cells.");
        System.out.println("Press enter to place a few organisms into this \"world\".");
        System.in.read(); System.in.read();// pause until user hits enter

        // Now setting a few to ants, doodlebugs, and super doodlebugs
        // just to try out the DisplayFrame
        data[1][3] = 'o';
        data[17][8] = 'o';
        data[4][18] = 'o';
        data[8][14] = 'x';
        data[19][19] = 'x';
        data[19][16] = 'S';

        // Reset the DisplayFrame, by calling setGrid with the updated char array
        // again with the values set as given in the char array
        disp.setGrid( data );

        System.out.println("Press enter again to see the organisms move");
        System.in.read(); System.in.read();// pause until user hits enter

        // Now changing positions of our ants, doodlebugs, and super doodlebugs
        data[1][4] = 'o';
        data[16][8] = 'o';
        data[5][18] = 'o';
        data[7][14] = 'x';
        data[19][0] = 'x';
        data[0][16] = 'S';
        // must reset original positions to spaces
        data[1][3] = ' ';
        data[17][8] = ' ';
        data[4][18] = ' ';
        data[8][14] = ' ';
        data[19][19] = ' ';
        data[19][16] = ' ';

        // Reset the DisplayFrame, by calling setGrid with the updated
        // char array again with the values set as given in the char array
        disp.setGrid( data );

    }

    ///////////////////////////////////////////////////////////////
}