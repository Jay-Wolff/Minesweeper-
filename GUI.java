import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.*;

class GUI extends JFrame {
	private boolean bombGrid[][];
	private int countGrid[][];
	private int numRows, numColumns, numBombs;
	private int RegularCell;
	private JButton cell[][];

	public GUI() {
		//creates the gride of the class uing a previously made method 
		//giving the number of rows and columns to be equivalent to 10 and
		//create 25 bombs
		this(10, 10, 25);
	}

	public GUI(int rows, int columns, int numBombs) {
		//creating the title of the game and setting it to where i want it to be placed 
		this.setTitle("MineSweeper");
		this.setSize(815,920);
		//rows and colums are new amounts meant for the primary numRows and numColumns
		this.numRows = rows;
		this.numColumns = columns;
		this.bombGrid = new boolean[numRows][numColumns];
		this.countGrid = new int [numRows][numColumns];
		//creates the amount of bombs used in the constructor's paramaters
		this.numBombs = numBombs;
		createBombGrid(); //fills in the placement of the bombs in the grid in order to 
		//properly formulate the 25 numBombs
		createCountGrid(); //fills in for the grid for the portions where the bomb does not exist and places a 
		//regular cell

		// initializing 2d array of button
		cell = new JButton[getNumRows()][getNumColumns()];
		//implements the grid method created in the Grid class implemented in here 
		setLayout(new GridLayout(getNumRows(), getNumColumns()));
		//creating the buttons for each cell as they are seperated in the grid function of the code 
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				cell[i][j] = new JButton();
				cell[i][j].addActionListener(new CellListener(i, j));
				// adding to frame using grid layout
				add(cell[i][j]);
			}
		}

		//the display's the close end option for the window 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //means whenever you click the x button it'll close
		this.setVisible(true); //whenever window is created it'll be visible to user
		this.setResizable(false); //the user cannot resize the window
	}

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

	//returns the bombs location when a place in the bombgrid is true
	public boolean isBombAtLocation(int row, int column) {
		return (bombGrid[row][column]);
	}

	//created this extra method to state when the user is at a set point 
	public boolean isAtPoint(int i, int j) {
		//returns the point for int i and j used in the creation of the count grid 
		return(i >= 0 && i < numRows && j >= 0 && j < numColumns);
	}

	public void createCountGrid() {
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

	//returns that there is no bomb at location and there is only the grid
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	//original getters restated
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

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

	public static void main(String args[]) {
		GUI test = new GUI();
	}

	// method to reset the grid for the next round of play
	private void reset() {
		createBombGrid(); //fills in the placement of the bombs in the grid in order to 
		//properly formulate the 25 numBombs
		createCountGrid(); //fills in for the grid for the portions where the bomb does not exist and places a 
		RegularCell = (getNumRows() * getNumColumns()) - getNumBombs();
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				//text is the number of bombs around
				cell[i][j].setText("");
				cell[i][j].setEnabled(true);
			}
		}
	}

	//implementing action listener
	private class CellListener implements ActionListener {
		int row, col;
		public CellListener(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// checking if there's a bomb in current location
			if (isBombAtLocation(row, col)) {
				// game over!
				// getting count grid
				int[][] counts = getCountGrid();
				// looping through each row and col
				for (int i = 0; i < getNumRows(); i++) {
					for (int j = 0; j < getNumColumns(); j++) {
						// displaying bomb if there's a bomb, else displaying
						// count
						if (isBombAtLocation(i, j)) {
							cell[i][j].setText("bomb");
						} else {
							cell[i][j].setText(String.valueOf(counts[i][j]));
						}
						// making button not clickable
						cell[i][j].setEnabled(false);
					}
				}
				// asking user if he likes to play again
				int status = JOptionPane.showConfirmDialog(null,"You're a Loser. Would you like to play again?", "Game over",JOptionPane.YES_NO_OPTION);
				// calling reset if user selected YES
				if (status == JOptionPane.YES_OPTION) {
					reset();
				} else {
					// else exit
					System.exit(0);
				}
			} else {
				// a non bomb cell is clicked, displaying count
				cell[row][col].setText(String.valueOf(getCountAtLocation(
						row, col)));
				// disabling button to prevent further clicks
				cell[row][col].setEnabled(false);
				// decrementing safeCount
				RegularCell--;
				// if safeCount hit 0, user won
				if (RegularCell == 0) {
					int status = JOptionPane.showConfirmDialog(null,"You won! Would you like to play again?", "Game over",JOptionPane.YES_NO_OPTION);
					if (status == JOptionPane.YES_OPTION) {
						reset();
					} else {
						System.exit(0);
					}
				}
			}
		} // action performed class ended for the action listener class
	} //cell listener class ended which implements the action listener class

}//end of entire code

