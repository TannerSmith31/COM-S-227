
package hw3;

import java.awt.Color;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * constructs an I piece with a cell list whose initial cells at each index are shown below
 * 
 *  . 0 .
 *  . 1 .
 *  . 2 .
 * 
 * @author Tanner Smith
 */
public class IPiece extends AbstractPiece {
	
	/**
	 * creates a new I piece.
	 * @param position - location of the upper left corner of the 3x3 "piece grid" with relation to the overall gameboard
	 * @param icons - list of icons (from BasicGenerator)
	 */
	public IPiece(Position position, Icon[] icons) throws IllegalArgumentException{
		super(position);
		if (icons.length != 3) {
	    	throw new IllegalArgumentException();
	    } else {
			Cell[] cells= new Cell[3];
			cells[0] = new Cell(icons[0], new Position(0, 1));		//creates new cell creates new cell with icons from icon list given and positions relative to the 3x3 grid for each shape
			cells[1] = new Cell(icons[1], new Position(1, 1));
			cells[2] = new Cell(icons[2], new Position(2, 1));
			setCells(cells);			//stores the list of cells in the abstract class
	    }
	}

	@Override
	public void transform() {
		// does nothing
	}
}
