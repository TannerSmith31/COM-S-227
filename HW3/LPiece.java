package hw3;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * constructs an L piece with a cell list whose initial cells at each index are given below
 * 
 * 	0 1 .
 * 	. 2 .
 * 	. 3 .
 * 
 * @author TRS31
 */

public class LPiece extends AbstractPiece {

	/**
	 * Creates a new L piece with a given position and list of icons (from BasicGenerator)
	 * @param position - where the upper left corner of the piece will be relative to the gameboard (given in BasicGenerator)
	 * @param icons - given list of random icons (given in BasicGenerator)
	 */
	public LPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position);
		if (icons.length != 4) {
	    	throw new IllegalArgumentException();
	    } else {
			Cell[] cells= new Cell[4];
			cells[0] = new Cell(icons[0], new Position(0, 0));		//creates new cell creates new cell with icons from icon list given and positions relative to the 3x3 grid for each shape
			cells[1] = new Cell(icons[1], new Position(0, 1));
			cells[2] = new Cell(icons[2], new Position(1, 1));
			cells[3] = new Cell(icons[3], new Position(2, 1));
			setCells(cells);			//stores the list of cells in the abstract class
	    }
	}

	/**
	 * transforms the L piece. There are 2 states of positions the array of cells can have:
	 * 
	 *   0 1 .		. 1 0
	 *   . 2 .	->	. 2 .
	 *   . 3 .		. 3 .
	 * 
	 * 
	 */
	@Override
	public void transform() {
		Cell[] cells = getCells();
		if (cells[0].getCol() == 0) {
			cells[0].setPosition(new Position(0,2));		//Changes the position of cell 0 from (0,0) to (0,2)
		} else {
			cells[0].setPosition(new Position(0,0));		//Changes the position of cell 0 from (0,2) to (0,0)
		}
		setCells(cells);
	}
}
