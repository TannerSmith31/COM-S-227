package hw3;

import api.Icon;
import api.Cell;
import api.Position;

/**
 * constructs a diagonal piece with a cell list whose initial cells at each index are shown below
 * 
 *  0 . 
 *  . 1 
 * 
 * @author Tanner Smith
 */
public class DiagonalPiece extends AbstractPiece {
	
	/**
	 * Creates a new corner piece with a given position and list of icons (from BasicGenerator)
	 * @param position - where the upper left corner of the piece will be relative to the gameboard (given in BasicGenerator)
	 * @param icons - given list of random icons (given in BasicGenerator)
	 */
	public DiagonalPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position);
		if (icons.length != 2) {
	    	throw new IllegalArgumentException();
	    } else {
			Cell[] cells= new Cell[2];
			cells[0] = new Cell(icons[0], new Position(0, 0));		//creates new cell creates new cell with icons from icon list given and positions relative to the 3x3 grid for each shape
			cells[1] = new Cell(icons[1], new Position(1, 1));
			setCells(cells);			//stores the list of cells in the abstract class
	    }
	}

	
	/**
	 * transforms the diagonal piece. There are 2 states of positions the array of cells can have:
	 * 
	 *  0 . 	 . 0 
	 *  . 1  -> 1 . 
	 */
	@Override
	public void transform() {
		if (getCells()[0].getCol() == 0) {
			Cell[] cells = getCells();
			cells[0].setPosition(new Position(0,1));
			cells[1].setPosition(new Position(1,0));
			setCells(cells);		//Sets the new position of the cells into the abstract class
			
		} else {
			Cell[] cells = getCells();
			cells[0].setPosition(new Position(0, 0));
			cells[1].setPosition(new Position(1, 1));
			setCells(cells);		//Sets the new position of the cells into the abstract class
		}
	}
}
