//only import the Random class for the placement of the bombs 
import java.util.Random;

//default constructor w/ no implementations for the instance variables 
public class Grid implements Runnable{
	private boolean bombGrid[][];
	private int [][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs =25;
	
//primary overloaded constructor 
	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;
		createBombGrid(); //fills in for the bombGrid in overloaded constructor 
		createCountGrid(); //fills in for the CountGrid in overloaded constructor 
	}
//second overloaded constructor 	
	public Grid(int rows, int columns) {
		//rows and colums are new amounts meant for the primary numRows and numColumns
		this.numRows = rows;
		this.numColumns = columns;
		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int [numRows][numColumns];
		this.numBombs = 25;
		createBombGrid(); //fills in for the bombGrid in overloaded constructor 
		createCountGrid(); //fills in for the CountGrid in overloaded constructor 
	}
//third overloaded constructor 
	public Grid(int rows, int columns, int numBombs) {
		//rows and colums are new amounts meant for the primary numRows and numColumns
		this.numRows = rows;
		this.numColumns = columns;
		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int [numRows][numColumns];
		//creates the amount of bombs used in the constructor's paramaters
		this.numBombs = numBombs;
		createBombGrid(); //fills in for the bombGrid in overloaded constructor 
		createCountGrid(); //fills in for the CountGrid in overloaded constructor 
	}
//returns the number of rows inputed
	public int getNumRows() {
		return numRows;
		
	}
//returns the number of columns inputted
	public int getNumColumns() {
		return numColumns;
	}
//retunrs the number od bombs inputed
	public int getNumBombs() {
		return numBombs;
	}
//returns the layout for the bomb grid
	public boolean[][] getBombGrid() {
		//creates a boolean equivalent to the layout of the bombgrid
		boolean[][] meow = new boolean[numRows][numColumns];
		//the for loops check to see the outerlayer of the grid is cresaated with the proper
		//amount the system is asking for which is why we use the original numRows and 
		//numColumns
		for (int i = 0; i < numRows; i++) {
			for(int j = 0; j < bombGrid[i].length; j++) {
				meow [i][j] = bombGrid[i][j];
			}
		}
		return meow;
	}
//returns the layout of the count of the grid given at the beginning
	public int[][] getCountGrid() {
		//creates a boolean equivalent to the layout of the contGrid
		int[][] reptile = new int[numRows][numColumns];
		//the for loops check to see the outerlayer of the grid is cresaated with the proper
		//amount the system is asking for which is why we use the original numRows and 
		//numColumns
		for (int i = 0; i < numRows; i++) {
			for(int j = 0; j < countGrid[i].length; j++) {
				reptile[i][j] = countGrid[i][j];
			}
		}
		return reptile;
	}
//returns the bombs location when a place in the bombgrid is true
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
//returns that there is no bomb at location and there is only the grid
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
//method that creates the bombs at certain points in the grid at random locations
	private void createBombGrid() {
		//made an object for the random import
		Random rand = new Random();
		//restated bombgrid to use numRows and numColumns
		bombGrid = new boolean[numRows][numColumns];
		//for loops that go through the rows and columns of the code in order to get the 
		//syntax for the 
		for(int i=0; i<numRows; i++){
			for(int j=0;j<numColumns;j++){
				bombGrid[i][j] = false;
			}
		}
		//for loop for the creatioion of the bombs on the grid
		for(int i=0; i<numBombs; i++){
			//for the x coordinates so the rows
			int mX = 0;
			//for the y coordinates so for the columns 
			int mY = 0;
			//do loop to choose a random placement for the bombs
			do {
				mX = rand.nextInt(numRows);
				mY = rand.nextInt(numColumns);
				//while loop to prove that there is a possibility to place a bomb in that specific box
			}while(bombGrid[mX][mY] == true);
				bombGrid[mX][mY] = true;
				//generating a new location for x and y
			}
	}
	//created this extra method to state when the user is at a set point 
	private boolean isAtPoint(int i, int j) {
		//returns the point for int i and j used in the creation of the count grid 
		return(i >= 0 && i < numRows && j >= 0 && j < numColumns);
	}
	//the method used to create the grid with the implementation of the bombs and revealing them
	private void createCountGrid() {
		//restating the previous method at the start with numRows and NumColumns
		countGrid = new int[numRows][numColumns];
		//the for loops check to get the proper amount on the grid 
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numColumns;j++){
				int bombs = 0;
				//the box the user is currently in 
				if(isAtPoint(i,j)){ 
					if(isBombAtLocation(i, j))
						bombs += 1;
				}
				//now the box to the left of the user their is a bomb and exists
				if(isAtPoint(i,j-1)){ 
					if(isBombAtLocation(i, j -1))
						bombs += 1;
				}
				 //stating that to the right of the user their is a bomb and exists
				if(isAtPoint(i,j+1)){
					if(isBombAtLocation(i, j + 1))
						bombs += 1;
				}
				//stating that to the top left box of the user their is a bomb and exists
				if(isAtPoint(i-1,j-1)){ 
					if(isBombAtLocation(i - 1, j - 1))
						bombs += 1;
				}
				//stating that right above the user their is a bomb and exists
				if(isAtPoint(i-1,j)){ 
					if(isBombAtLocation(i - 1, j))
						bombs += 1;
				}
				//stating that to the top right of the user their is a bomb and exists
				if(isAtPoint(i-1,j+1)){ 
					if(isBombAtLocation(i - 1, j + 1))
						bombs += 1;
				}
				//stating that above of the user their is a bomb and exists
				if(isAtPoint(i + 1,j)){
					if(isBombAtLocation(i + 1, j))
						bombs += 1;
				}
				//stating that to the bottom right of the user their is a bomb and exists
				if(isAtPoint(i+1,j+1)){ // bottom right
					if(isBombAtLocation(i + 1, j + 1))
						bombs += 1;
				}
				//stating that to the bottom left of the user their is a bomb and exists
				if(isAtPoint(i+1,j-1)){ 
					if(isBombAtLocation(i + 1, j - 1))
						bombs += 1;
				}
				//the creation of the grid with all if statements above running through
				countGrid[i][j] = bombs;
			}
		}
	}
	
	//de main string for de grid class 
	public static void main(String [] args) {
		Grid test = new Grid();	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}



}
