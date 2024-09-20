import java.util.Stack;
import java.util.ArrayList;





public class maze extends mazebase
{
    // default constructor suffices and is equivalent to
    // public maze() { super(); }

	private Coord[][] Path; // share array for solve() and trace()



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

/*
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
			
			
			if (new_row >= 0 && new_row < mheight-1 && new_col >= 0 && new_col < mwidth-1 && M[new_row][new_col] > 0 && M[new_row][new_col] < smallest_value) {

				smallest_row = new_row;
				smallest_col = new_col;
				smallest_value = M[new_row][new_col];

			}
		}
		//After this loop we have the slammest next location and have to draw it and increase the locations number
		drawblock(row, col);
		drawdot(smallest_row, smallest_col);
		nextframe(20);

		M[smallest_row][smallest_col] += 1;

		row = smallest_row;//go to the next coordinates
		col = smallest_col;


	}		
	





}//solve() uses the basic algorythm

*/


class Coord {
    private final int row;
    private final int col;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

/*

@Override
public void solve() { // uses the DFS

	M[mheight-3][mwidth-2] = 1;//This is different than in others, because of how my system renders the window
	drawblock(mheight-3, mwidth-2); //I have to call next frame before anything shows up
	nextframe(40);

	Path = new Coord[mheight][mwidth];	//is used to check if it was visited

	ArrayList<Coord> Stack = new ArrayList<>();	//this is the stack to do the recursive steps
	Stack.add(new Coord(1, 1));	//append the starting coordinates	
		
	int[] ROW = {-1, 0, 1, 0}; // they will be NORTH, SOUTH, EAST, WEST
	int[] COL = {0, 1, 0, -1};


	while (Stack.size() > 0) {
		Coord current = Stack.remove(Stack.size() - 1);//get the current position
		drawdot(current.getRow(), current.getCol());
		nextframe(10);

		if (current.getRow() == mheight-3 && current.getCol() == mwidth-2) { //if we are at the exit
			System.out.println("FOUND THE EXIT");
			break; //ao maybe change it to return something, not sure yet

		}
		

		for (int index = 0; index < 4; index++) {
			int next_row = current.getRow() + ROW[index];
			int next_col = current.getCol() + COL[index];

			//here I will have to check the if it is in bounds, is digged out(M[][] ==1) and is not in Path(means not visited), then I will add them to the Stack	
				
			
			if (next_row >= 0 && next_row < mheight-1 && next_col >= 0 && next_col < mwidth-1 && M[next_row][next_col] == 1 && Path[next_row][next_col] == null) {
				Stack.add(new Coord(next_row, next_col));
				//here I will need to add to path, because here I still have the handle to the place they come from, the current.getRow() and current.getCol()
				Path[next_row][next_col] = new Coord(current.getRow(), current.getCol());

			}

		}	

	}




}//solve()

*/



@Override
public void solve() { //uses BFS
	
	
	M[mheight-3][mwidth-2] = 1;//This is different than in others, because of how my system renders the window
	drawblock(mheight-3, mwidth-2); //I have to call next frame before anything shows up
	nextframe(40);

	
	Path = new Coord[mheight][mwidth];	//is used to check if it was visited

	ArrayList<Coord> Queue = new ArrayList<>();	//this is the queue for the algorythm
	Queue.add(new Coord(1, 1));	//append the starting coordinates	
	int head = 0; //this is pointer tothe first value, that will increase evrytim ei use it, like popleft() in python

	
	int[] ROW = {0, -1, 0, 1}; // they will be ->, down, <-, up
	int[] COL = {1, 0, -1, 0};

	while(head < Queue.size()) {
		Coord current = Queue.get(head++);
		
		drawdot(current.getRow(), current.getCol());
		nextframe(10);
		drawblock(current.getRow(), current.getCol());	//WANNA SEE HOW IT FULLY WORKS REMOVE THIS LINE OR COMMENT IT OUT!!!
			
	

		if (current.getRow() == mheight-3 && current.getCol() == mwidth-2) {
			System.out.println("FOUND THE EXIT");
			break;

		}
		

		for (int index = 0; index < 4; index++) {
			int next_row = current.getRow() + ROW[index];
			int next_col = current.getCol() + COL[index];

			if (next_row >= 0 && next_row < mheight-1 && next_col >= 0 && next_col < mwidth-1 && M[next_row][next_col] == 1 && Path[next_row][next_col] == null) {
				Queue.add(new Coord(next_row, next_col));
				Path[next_row][next_col] = new Coord(current.getRow(), current.getCol());				

	
			}
		}	
		


	}



}//solve()



@Override
public void trace() {
	Coord current = Path[mheight-3][mwidth-2];

	drawdot(mheight-3, mwidth-2);	//dram the exit
	nextframe(10);

	while (current.getRow() != 1 || current.getCol() != 1) {
		drawdot(current.getRow(), current.getCol());
		nextframe(10);
		
		current = Path[current.getRow()][current.getCol()];

	}

	drawdot(1, 1);// draw the start
	nextframe(10);



}//trace()












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
	//showvalue = true;

	//I want to add a gif
	
	String gifname ="rock-hyrax.gif";
	boolean usegif=true;
	
    }

}//maze subclass



