import java.util.Stack;






public class maze extends mazebase
{
    // default constructor suffices and is equivalent to
    // public maze() { super(); }

 @Override
 public void digout(int row, int col)   // modify this function
 {
     // The following is a skeleton program that demonstrates the mechanics
     // needed for the completion of the program.

     // We always dig out two spaces at a time: we look two spaces ahead
     // in the direction we're trying to dig out, and if that space has
     // not already been dug out, we dig out that space as well as the
     // intermediate space.  This makes sure that there's always a wall
     // separating adjacent corridors.
	/*	
     M[y][x] = 1;  // digout maze at coordinate y,x
     drawblock(y,x);  // change graphical display to reflect space dug out
     //nextframe(40); // show next animation frame after 40ms delay
	*/
     // But the following won't work (but will compile)

     // sample code that tries to digout one space to the left:
     //if (x-1>=0) digout(y,x-1);
     // sample code that tries to digout TWO space to the right IF it's not
     // already dug out:
	/*
     	if (x+2<mwidth && M[y][x+2]==0) // always check for maze boundaries
	 {
	     M[y][x+1] = 1;
	     drawblock(y,x+1);
	     digout(y,x+2);
	 }
	*/

// y is row and x is col	

	int[] ROW = {-1, 0, 1, 0}; // they will be NORTH, SOUTH, EAST, WEST
	int[] COL = {0, 1, 0, -1};
	int[] P = {0, 1, 2, 3}; //will give me random directions

	int new_row = row;
	int new_col = col;


	//I think here I have to check if it is not the borders	
     	M[row][col] = 1;  // digout maze at coordinate y,x
    	drawblock(row,col);  // change graphical display to reflect space dug out
    	//nextframe(40);  show next animation frame after 40ms delay
	


	for (int i = 0; i < P.length-1; i++) { //the permutations
		int r = i + (int)(Math.random() * (P.length-i));	//r is between i and P.length-1
		int temp = P[i];
		P[i] = P[r];
		P[r] = temp;


	}

	for (int dir: P) {//for each of the directions
		new_row = row + ROW[dir] * 2;// to check 2 infront
		new_col = col + COL[dir] * 2;
		
		if (new_row >= 0 && new_row < mheight-1 && new_col >= 0 && new_col < mwidth-1 && M[new_row][new_col] == 0) {
			M[row + ROW[dir]][col + COL[dir]] = 1;
			drawblock(row+ROW[dir], col+COL[dir]);
			digout(new_row, new_col);						


		}	


	}



 }//digout
@Override
public void solve() {
	//have to dig out the exit
	M[mheight-3][mwidth-2] = 1;//This is different than in others, because of how my system renders the window
	drawblock(mheight-3, mwidth-2); //I have to call next frame before anything shows up
	nextframe(40);

	int[] ROW = {-1, 0, 1, 0}; // they will be NORTH, SOUTH, EAST, WEST
	int[] COL = {0, 1, 0, -1};

	
	int row = 1;
	int col = 1;
	drawdot(row, col);
	nextframe(40);
	//aslo I might have to increase the number of this coordinate
	M[row][col] += 1;

	
	while (row != mheight-3 || col != mwidth-2) {
		int smallest_row = row;
		int smallest_col = col;
		int smallest_value = 0x7fffffff;	

		for (int index = 0; index < 4; index++) { //check all the sides to find the smallest position greater than 0
			int new_row = row + ROW[index];
			int new_col = col + COL[index];
			System.out.println("new possible direction: " + new_row + " " + new_col);	
			
			
			if (new_row >= 0 && new_row < mheight-1 && new_col >= 0 && new_col < mwidth-1 && M[new_row][new_col] > 0 && M[new_row][new_col] < smallest_value) {

				smallest_row = new_row;
				smallest_col = new_col;
				smallest_value = M[new_row][new_col];

			}
		}
		//After this loop we have the slammest next location and have to draw it and increase the locations number
		System.out.println("New direction: " + smallest_row + " " + smallest_col);	
		drawblock(row, col);
		drawdot(smallest_row, smallest_col);
		nextframe(20);

		M[smallest_row][smallest_col] += 1;

		row = smallest_row;//go to the next coordinates
		col = smallest_col;


	}		
	





}


    public static void main(String[] av)
    {
	new maze(); // constructor of superclass will initiate everything
    }

    // other hints:  override customize to change maze parameters:
    @Override
    public void customize()
    {
	// ... can change mwidth, mheight, bw,bh, colors here
	mwidth = 80;
	mheight = 40;
	wallcolor = java.awt.Color.white;
	//bw -= 8;
	//bh -=8;
	showvalue = true;
    }

}//maze subclass


