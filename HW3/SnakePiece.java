package hw3;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * constructs a snake piece with a cell list whose initial cells at each index are shown below
 * 
 * 	0 . .
 * 	1 2 3
 *  . . .
 *  
 * @author Tanner Smith
 */

public class SnakePiece extends AbstractPiece
{
	private int sequenceNum;
  /**
   * Sequence of positions for the first cell.
   * if you label a grid by the positions that each index occupies, you get the following
   * 
   * 	*********************
   * 	*  0   *  1   *  2  *
   *    *********************
   *    * 5&11 * 4&10 * 3&9 *
   *    *********************
   *    *  6   *  7   *  8  *
   *    *********************
   */
  private static final Position[] SEQUENCE = {
      new Position(0, 0),		//SEQUENCE index 0
      new Position(0, 1),		//SEQUENCE index 1
      new Position(0, 2),		//SEQUENCE index 2
      new Position(1, 2),		//SEQUENCE index 3
      new Position(1, 1),		//SEQUENCE index 4
      new Position(1, 0),    	//SEQUENCE index 5
      new Position(2, 0),		//SEQUENCE index 6
      new Position(2, 1),		//SEQUENCE index 7
      new Position(2, 2),		//SEQUENCE index 8
      new Position(1, 2),		//SEQUENCE index 9
      new Position(1, 1),		//SEQUENCE index 10
      new Position(1, 0),		//SEQUENCE index 11
  };
  
 /**
  * creates a new snake piece using a position to start relative to the gameboard and a list of random icons (created by
  * the BasicGenerator). The snake piece moves in a sequence of positions and initialy, the cells are in
  * indexes [0, 11, 10, 9]. A sequence number is used to keep track of where in its transformation cycle to piece is.
  * @param position - where the upper left corner of the piece will be relative to the entire gameboard (given in BasicGenerator)
  * @param icons - given list of random icons (given in BasicGenerator)
  */
  public SnakePiece(Position position, Icon[] icons) throws IllegalArgumentException{
    super(position);
    if (icons.length != 4) {
    	throw new IllegalArgumentException();
    } else {
    	Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0, 0));		//creates new cell with icons from icon list given and positions relative to the 3x3 grid for each shape
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(1, 1));
		cells[3] = new Cell(icons[3], new Position(1, 2));
		setCells(cells);			//stores the list of cells in the abstract class
		sequenceNum = 1;			//Has to start at 1 because if it started at 0 it wouldn't start moving until the 2nd time you hit the transform button
    }
  }

  /**
   * transforms the snake piece through its path given by the SEQUENCE above.
   * the SEQUENCE is a figure-eight pattern as shown below:
   * 
   *  0 . .	   1 0 .	  2 1 0	   3 2 1	  . 3 2	   . . 3	  . . .
   *  1 2 3	-> 2 3 . -> 3 . . -> . . 0 -> . 0 1 -> 0 1 2 -> 1 2 3 -> and so on...
   *  . . .	   . . .	  . . .	   . . .	  . . .	   . . .	  0 . .
   */
  @Override
  public void transform() {	
	  Cell[] cells = getCells();
	  for (int i = 0; i < cells.length; i++) {
		  
		  if (sequenceNum - i < 0) {		//accounts for the error of negative indexes
			  cells[i].setPosition(SEQUENCE[12 + (sequenceNum - i)]);			//because the transforming shift is a closed loop, if (sequenceNum - i) is negative number, then you want to subtract that number from the number of positions in the sequence (i.e. the sequence number -1 is the same as sequence number 11 because the loop goes ..8,9,10,11,0,1,2... so 11 is right before 0)
		  } else {
			  cells[i].setPosition(SEQUENCE[sequenceNum - i]);		//because the indexes of the original snake shape are in the sequence positions backwards (starting at 0,0) the number in the sequence to use is sequenceNum - i
		  }
	  }
	  //ADJUST SEQUENCE NUBMER
	  if (sequenceNum == 11) {			//if sequence number has reached the max index of the sequence list (index 11)
		  sequenceNum = 0;					//reset the sequence number to the beginning (index 0)
	  } else {							//else
		  sequenceNum ++;					//increment the sequence number by one
	  }
	  
	  setCells(cells);
	  
  }
}
