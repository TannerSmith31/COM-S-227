package hw3;

import api.Icon;
import api.Cell;
import api.Position;
import examples.SamplePiece;
import api.Piece;

/**
 * Abstract superclass for implementations of the Piece interface.
 * @author Tanner Smith
 */
public abstract class AbstractPiece implements Piece {
	private Position position;
	private Cell[] cells;

	/**
	 * Constructs a piece with the given position. Subclasses extending this class
	 * MUST call setCells to initialize initial cell positions and icons.
	 * 
	 * @param position initial position for upper-left corner of bounding box
	 */
	protected AbstractPiece(Position position) { // TODO Auto-generated method stub
		this.position = position;
		
	}

	/**
	 * returns the position of the piece
	 */
	@Override
	public Position getPosition() {
		return position;
	}

	/**
	 * sets the given cells as the cells for this piece
	 * @param givenCells - list of cells to become the cells of this piece
	 */
	@Override
	public void setCells(Cell[] givenCells) {		//gained inspiration from SamplePiece
		// deep copy the given array
		cells = new Cell[givenCells.length];
		for (int i = 0; i < givenCells.length; i++) {
			cells[i] = new Cell(givenCells[i]);
		}
	}

	/**
	 * gets a copy of all the cells stored in this piece
	 * @return - copy of all stored cells
	 */
	@Override
	public Cell[] getCells() {
		// deep copy this object's cell array
		Cell[] copy = new Cell[cells.length];
		for (int i = 0; i < cells.length; i++) {
			copy[i] = new Cell(cells[i]);
		}
		return copy;
	}

	
	/**
	 * gets the position of cells relative to the gameboard as a whole
	 * @return positions of cells relative to gameboard
	 */
	@Override
	public Cell[] getCellsAbsolute() {		//gained inspiration from SamplePiece
		Cell[] ret = new Cell[cells.length];
		for (int i = 0; i < cells.length; i++) {
			int row = cells[i].getRow() + position.row();
			int col = cells[i].getCol() + position.col();
			Icon b = cells[i].getIcon();
			ret[i] = new Cell(b, new Position(row, col));
		}
		return ret;
	}

	/**
	 * moves the piece one unit down
	 */
	@Override
	public void shiftDown() {
		position = new Position(position.row() + 1, position.col());
	}

	/**
	 * moves the piece one unit left
	 */
	@Override
	public void shiftLeft() {
		position = new Position(position.row(), position.col() - 1);
	}

	/**
	 * moves the piece one unit right
	 */
	@Override
	public void shiftRight() {
		position = new Position(position.row(), position.col() + 1);
	}

	/**
	 * cycles each of the icons in a piece to the next cell in the cell list for the piece.
	 * the icon of the last cell in the cell list moves to the first cell in the cell list.
	 */
	@Override
	public void cycle() {
		Cell[] cycled = getCells();							//new cell list that will contain cycled cells
		Icon finalCell = cells[cells.length-1].getIcon();	//variable to store the icon of the final cell in current cells list CELLS
		cycled[0].setIcon(finalCell);						//setting the first cell of the cycled cells with the icon of the last cell in CELLS
		for (int i = 1; i < cells.length; i++) {			//for indexes 1 to the end, the icon at each index of cycled will take on the icon of the cell in the previous index of CELLS
			cycled[i].setIcon(cells[i-1].getIcon());
		}
		setCells(cycled);

	}

	/**
	 * clones the given piece
	 * @return - a clone of the given piece
	 */
	@Override
	public Piece clone() {	//Gained inspiration samplePiece
		try
		{
			// call the Object clone() method to create a shallow copy
			AbstractPiece s = (AbstractPiece) super.clone();

			// then make it into a deep copy (note there is no need to copy the position,
			// since Position is immutable, but we have to deep-copy the cell array
			// by making new Cell objects
			s.cells = new Cell[cells.length];
			for (int i = 0; i < cells.length; ++i)
			{
				s.cells[i] = new Cell(cells[i]);
			}
			return s;
		}
		catch (CloneNotSupportedException e)
		{
			// can't happen, since we know the superclass is cloneable
			return null;
		}
	}
	
	/**
	 * abstract method that is used to transform pieces (defined in the subclasses)
	 */
	public abstract void transform();

}
