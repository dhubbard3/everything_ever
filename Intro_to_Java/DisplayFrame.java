import javax.swing.*;
import java.util.*;

// Displays a frame containing a grid,
// each cell containing a value given in the input array, data
public class DisplayFrame extends JFrame {
    private GridPanel grid;

    // accepts string title,
    //  x: # of columns and
    //  y: # of rows
    public DisplayFrame(String title, int x, int y) {
        setTitle( title );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );

        grid = new GridPanel(y, x);
        add( grid );

        pack();
        setVisible( true );
    }

    // Sets grid cells to values in array, data
    public void setGrid( char [][] data ) {
        for (int i = 0; i < data.length; i++ ) {
            for (int j = 0; j < data[i].length; j++ ) {
                grid.setGrid( i, j, data[i][j] );
            }
        }
    }
}