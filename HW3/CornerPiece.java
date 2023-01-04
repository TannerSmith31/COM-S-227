package hw3;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * constructs a corner piece with a cell list whose initial cells at each index are shown below
 * 
 *  0 .
 *  1 2
 * 
 * @author Tanner Smith
 */
public class CornerPiece extends AbstractPiece {
	
	
	/**
	 * Creates a new corner piece with a given position and list of icons (from BasicGenerator)
	 * @param position - where the upper left corner of the piece will be relative to the gameboard (given in BasicGenerator)
	 * @param icons - given list of random icons (given in BasicGenerator)
	 */
	public CornerPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position);
		if (icons.length != 3) {
	    	throw new IllegalArgumentException();
	    } else {
	    	Cell[] cells= new Cell[3];
			cells[0] = new Cell(icons[0], new Position(0, 0));		//creates new cell creates new cell with icons from icon list given and positions relative to the 3x3 grid for each shape
			cells[1] = new Cell(icons[1], new Position(1, 0));
			cells[2] = new Cell(icons[2], new Position(1, 1));
			setCells(cells);			//stores the list of cells in the abstract class
	    }
	}

	
	/**
	 * transforms the corner piece. There are 4 states of positions the array of cells can have:
	 *  
	 *  0 . 	 1 0 	  2 1      . 2 
	 *  1 2  ->  2 .  ->  . 0  ->  0 1 
	 * 
	 */
	@Override
	public void transform() {
		Cell[] cells = getCells();
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].getRow() == 0 && cells[i].getCol() == 0) {				//If cell is at (0,0)
				cells[i].setPosition(new Position(0,1));					//Move to (0,1)
			} else if (cells[i].getRow() == 0 && cells[i].getCol() == 1) {		//If cell is at (0,1)
				cells[i].setPosition(new Position(1,1));					//Move to (1,1)
			} else if (cells[i].getRow() == 1 && cells[i].getCol() == 1) {		//If cell is at (1,1)
				cells[i].setPosition(new Position(1,0));					//Move to (1,0)
			} else {															//If cell is at (1,0) *the only spot not called
				cells[i].setPosition(new Position(0,0));					//Move to (0,0)
			}
		}
		setCells(cells);	//sets the new cells into the abstract class
	}
}
