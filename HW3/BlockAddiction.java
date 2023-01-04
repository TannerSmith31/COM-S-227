
package hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.AbstractGame;
import api.Generator;
import api.Icon;
import api.Position;


public class BlockAddiction extends AbstractGame {
	
	
	/**
	 * Creates a new game with a gameboard grid size given by the player as well as a generator to be used to 
	 * be the games source of randomness and piece creation.
	 * @param height - The height of the gameboard grid
	 * @param width - The width of the gameboard grid
	 * @param gen - generator object to be used as this games source of randomness & piece creation
	 */
	public BlockAddiction(int height, int width, Generator gen) {	// TODO WHAT MORE IS THERE?
		super(height, width, gen);
	}

	/**
	 * Creates a new game with a gameboard grid size given by the player as well as a generator to be used to 
	 * be the games source of randomness and piece creation. In addition, this generator prefills a given number
	 * of rows starting at the bottom of the board and going up with a checkerboard pattern of icons.
	 * @param height - The height of the gameboard grid
	 * @param width - The width of the gameboard grid
	 * @param gen - generator object to be used as this games source of randomness & piece creation
	 * @param preFillRows - The number of rows the player wants prefilled
	 */
	public BlockAddiction(int height, int width, Generator gen, int preFillRows) {		// TODO
		super(height, width, gen);
		if (preFillRows > 0) {
			preFill(preFillRows, gen);		//This is what actually prefills the rows
		}
	}

	/**
	 * Determines the positions on the gameboard of the cells that need to collapse (in our case,
	 * whenever 3 cells of the same color are touching). The code does this by going through each cell
	 * on the gameboard and, if it is null, it skips it, and if it comes acrossed a filled cell, it stores the
	 * Icon of that cell and compares it to the icons of the 4 cells around it in a clockwise pattern (up, right, down, left).
	 * The actual collapsing of these cells occurs elsewhere in the code.
	 * @return - an arrayList of the positions that need to be collapsed.
	 */
	
	@Override
	public List<Position> determinePositionsToCollapse() {
		List<Position> positions = new ArrayList<>();
		Icon currIcon;
		for (int row = 0; row < getHeight(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				currIcon = getIcon(row, col);	//storing current positions icon to be used as a reference
				if (currIcon == null) {			//Accounts for the fact that null == null so any blank spaces get added to the remove positions list
					continue;
				}
				List<Position> possiblePositions = new ArrayList<>();
				possiblePositions.add(new Position(row,col));				//adds the current position
				
				//checking cells around the current cell for touching color matches (must ".equals()" to compare icons!!)
				if (row - 1 >= 0 && currIcon.equals((getIcon(row - 1, col)))) {	//Checking icon above the position ("row - 1 >= 0" avoids out of bounds error at the top of the board)
					possiblePositions.add(new Position(row - 1, col));
				}
				if (col + 1 < getWidth() && currIcon.equals(getIcon(row, col + 1))) {	//checking icon right of the position ("col + 1 < getWidth()" avoids out of bounds error at the rightmost edge of the board)
					possiblePositions.add(new Position(row, col + 1));
				}
				if (row + 1 < getHeight() && currIcon.equals(getIcon(row + 1, col))) {	//checking icon below the position ("row + 1 < getHeight()" avoids out of bounds error on bottom of board)
					possiblePositions.add(new Position(row + 1, col));
				}
				if (col - 1 >= 0 && currIcon.equals(getIcon(row, col - 1))) {	//checking icon left of the position ("col - 1 >= 0" avoids out of bounds error at the leftmost edge of the board
					possiblePositions.add(new Position(row, col - 1));
				}
				
				//Checking whether 3 matching icons are touching
				if (possiblePositions.size() >= 3) {
					for (Position currPosition : possiblePositions) {
						if (!(positions.contains(currPosition))){	//avoids duplication by only adding to the list if the
							positions.add(currPosition);			//position isn't already in the list.
						}
					}
				}
			}
		}
		Collections.sort(positions);	//we sort the list...I guess?
		return positions;
	}

	/**
	 * Fills in a given number of rows at the bottom of the game board with a checkerboard pattern of random icons
	 * starting with the bottom row having the first cell empty and the second cell full and the second
	 * row having the first cell filled and the second empty, and alternates between these as it goes up.
	 * @param rows - number of rows to be prefilled
	 * @param gen - the generator to be used in creating the random icons
	 */
	private void preFill(int rows, Generator gen) {
		int startIndex = 1;				//tells whether to put the first block on the far left or one unit over (very bottom row's first cell is 1 over so this starts as 1)
		int width = getWidth();			//width of the current game board
		int height = getHeight();		//height of the current game board
		for (int currRow = height - 1; currRow > height - rows - 1; currRow--) {
			if (startIndex == 0) {
				for (int col = 0; col < width; col += 2) {			
					Icon randomIcon = gen.randomIcon();
					setBlock(currRow, col, randomIcon);
				}
				startIndex = 1;
			} else {				//Start index is 1
				for (int col = 1; col < width; col += 2) {
					Icon randomIcon = gen.randomIcon();
					setBlock(currRow, col, randomIcon);
				}
				startIndex = 0;
			}
		}
	}
	
	
	
//	/**
//	 * ALL OF THE BELOW CODE PERTAINS TO A RECURSIVE WAY OF DETERMINING WHICH POSITIONS SHOULD COLLAPSE.
//	 */
//	
//	//instance variables
//	private ArrayList<Position> checkedPos = new ArrayList<Position>();	//List of cells that have been checked by the recursion (but might not have had the right color)
//	private ArrayList<Position> matchPos = new ArrayList<Position>();	//List of cells that have been checked by the recursion and had the right color
//	private ArrayList<Position> checkedMatches = new ArrayList<Position>(); 	//List of cells that have already been checked (whether they were a group of 3 or more and were added to the removed positions, or they are a group of 2 that didn't colapse but has no need to be checked again)
//	
//	/**
//	 *method for determining which positions should be collapsed that uses recursion
//	 *(uses checkPositions helper method recursively)
//	 */
//	@Override
//	public List<Position> determinePositionsToCollapse() {
//		List<Position> positions = new ArrayList<>();	//used to store the positions to be collapsed
//		for (int row = 0; row < getHeight(); row++) {
//			for (int col = 0; col < getWidth(); col++) {
//				if (!checkedMatches.contains(new Position(row,col))) {  //keeps the program from checking cells that have already been checked and failed in the past
//					checkPosition(row, col); //Recursive Step to create a possible group of similar colors
//					
//					if (matchPos.size() >= 3) {		//Checks to see if there are 3 of the same icons touching
//						for (Position currPosition : matchPos) {
//							if (!positions.contains(currPosition)) { //Because of the checkedMatches array list, I believe duplication isn't an issue and therefore this line is unnecessary
//								positions.add(currPosition);
//							}
//						}
//					}
//					for (Position currPosition : matchPos) { //if the matched positions were greater than 3, they are already accounted for in the positions to collapse and there is no need to check these cells again, and if they were a group of less than 3 there is still no need to check them again)
//						checkedMatches.add(currPosition);
//					}
//					//clearing out the 2 lists after each cell checked to start anew each time
//					checkedPos.clear();
//					matchPos.clear();
//				}
//			}
//		}
//		checkedMatches.clear();
//		Collections.sort(positions);	//we sort the list...I guess?
//		return positions;
//	}
//	
//	/**
//	 * Helper method for the recursive way of figuring out which positions should collapse through finding icons
//	 * of the same color that touch each other. It first gets called on a single cell and checks the cells around 
//	 * it (in a clockwise fashion starting from the top) to see whether any match the current "central" cell's color. 
//	 * If one of the touching cells does match the central cell, the checkPosition method is then called with that cell 
//	 * as the central cell and checks all touching it to find more matches. If a touching cell matches the central cell, 
//	 * it is added to the "matchPos" and "checkedPos" lists & recursively checked. otherwise, the touching cell didn't 
//	 * have the same color and is added to only the "checkedPos" list. 
//	 * The base case is that all surrounding cells (up,left,down,right) have already been checked.
//	 * @param row - row of the "central cell" that the method is being called on
//	 * @param col - column of the "central cell" that the method is being called on
//	 */
//	private void checkPosition(int row, int col) {
//		Icon color = getIcon(row, col);
//		if (color != null) {
//			checkedPos.add(new Position(row,col));
//			matchPos.add(new Position(row, col));
//			int upper = row - 1;  int right = col + 1;  int lower = row + 1;  int left = col - 1; 	//determines locations
//			if (row != 0 && !checkedPos.contains(new Position(upper, col)) && color.equals(getIcon(upper, col))){
//				checkPosition(upper, col);
//			} else {
//				checkedPos.add(new Position(upper, col));
//			}
//			if (col != getWidth() - 1 && !checkedPos.contains(new Position(row, right)) && color.equals(getIcon(row, right))) {
//				checkPosition(row, right);
//			} else {
//				checkedPos.add(new Position(row, right));
//			}
//			if (row != getHeight() - 1 && !checkedPos.contains(new Position(lower, col)) && color.equals(getIcon(lower, col))) {
//				checkPosition(lower, col);
//			} else {
//				checkedPos.add(new Position(lower, col));
//			}
//			if (col != 0 && !checkedPos.contains(new Position(row, left)) && color.equals(getIcon(row, left))) {
//				checkPosition(row, left);
//			} else {
//				checkedPos.add(new Position(row, left));
//			}
//		return; //base case: all surrounding cells have been checked
//		}
//	}
}
