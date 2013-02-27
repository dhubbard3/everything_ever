// Assignment #: 11
// Name: Dave Hubbard
// StudentID: 1202945271
// Lecture: 3
// Description: The MazeSolver contains two 2-dimetional character arrays,
//              one for the original and another to display the
//              solution to the maze, and the size of maze.
//              Its findSolution method solves the maze problem
//              and put the solution in the stack.


import java.util.Stack;

public class MazeSolver
 {
  private char[][] originalMaze;
  private char[][] maze;
  private int mazeSize;
  private Stack<Position> stackSoln;

  //Constructor to initialize the mazeSize,
  //initializes two 2-dimensional character arrays.
  public MazeSolver(String[] mazeInfo)
   {
     mazeSize = 10;
     setupMaze(mazeInfo);
     stackSoln = new Stack<Position>();
   }

  //the setupMaze method initializes
  //two character arrays, using the input array of strings.
  public void setupMaze(String[] mazeInfo)
   {
     maze = new char[mazeSize][mazeSize];
     originalMaze = new char[mazeSize][mazeSize];
     for (int i=0; i<mazeSize; i++)
      {
      for (int j=0; j<mazeSize; j++)
       {
          originalMaze[i][j] = mazeInfo[i].charAt(j);
          maze[i][j] = originalMaze[i][j];
       }
      }
   }


  //The displayPath methods returns a string describing
  //how to go from the starting position to the goal position
  public String displayPath()
   {
     String result = "";

     while (stackSoln.isEmpty() == false)
      {
        result = stackSoln.pop() + result;
      }
     return "\nSolution Path:\n" + result+"\n\n";
   }


  //the displaySoln method returns a string containing
  //a solution of the maze
  public String displaySoln()
   {
    String result = "\nThe maze content:\n";

     for (int i = 0; i < maze.length; i++)
      {
       for (int j = 0; j < maze[i].length; j++)
        {
            result += maze[i][j];
        }
       result += "\n";
      }

     return result +"\n";
   }


  //The findSolution will return true if a solution is found,
  //false otherwise. Please see the pseudo-code of the assignment 11 statement
  public boolean findSolution()
   {
    boolean success = false; //finish should become true when a solution is found or it is determined that there is no solution
    boolean finish = false;  //success should become true when a solution is found

    //The following can be used to compute each of 8 directions
    //one can move from their current position (row,column).
    int[][] offset ={
                        {1,0},   //Down
                        {1,-1},  //DownLeft
                        {0,-1},  //Left
                        {-1,-1}, //UpLeft
                        {-1,0},  //Up
                        {-1,1},  //UpRight
                        {0,1},   //Right
                        {1,1}    //DownRight
                    };

	Position start = new Position(0,mazeSize-1,-1);
	stackSoln.push(start);

    while (finish == false && stackSoln.isEmpty( ) == false)
     {

        System.out.println("Trying the position of row "
                           + stackSoln.peek().getRow()
                           + " and column "
                           + stackSoln.peek().getColumn() );

	   char check = 'N'; //'N' represents no significant character.
	   int faceDir = 0; // Indicates the direction currently faced.[0=down,7=DownRight etc...]
	   int max = 8; 	 //The max number of directions to check (max # of rows in offset array).
	   int rowCheck = stackSoln.peek().getRow(); 	// Current row position
	   int colCheck = stackSoln.peek().getColumn(); // Current Column position
	   int newRow = 0;	 // New row position used for checking and setting maze array.
	   int newCol = 0;	 // New Column position for checking and setting maze array.

       int i = 0;
       while(check != '.' && i < max){

		   newRow = rowCheck + offset[i][0];	// adds offset to current position based on
		   newCol = colCheck + offset[i][1];	// direction (faceDir = row of offset array and face value).
		   faceDir = i;

		   // checks maze character at offset position if within the maze.
		   if(newRow < mazeSize && newCol < mazeSize){
			   if(newRow >= 0 && newCol >= 0)
		   			check = maze[newRow][newCol];
		   }

		   // checks if next position is the goal.
		   if(check == '<'){
			   stackSoln.peek().setFace(faceDir);
			   return true;
		   }

		   i++;
	    }

		// Removes top of stack if there is no available adjacent position. If stack is empty, no solution.
		if(check != '.'){

		   if(maze[rowCheck][colCheck] == 'x')
		   	   maze[rowCheck][colCheck] = 'O';

		   stackSoln.pop();

		   if (stackSoln.isEmpty())
			   finish = true;
			   success = false;
		}
		// Set maze array characters and add to solution stack.
		else{
		   stackSoln.peek().setFace(faceDir);
		   maze[newRow][newCol] = 'x';
		   Position newPos = new Position(newRow,newCol,-1);
		   stackSoln.push(newPos);
		}
     }

   return success;

   }//end of findSolution method

}//end of the MazeSolver class

