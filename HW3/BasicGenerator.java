
package hw3;

import java.util.Random;

import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;
import examples.SamplePiece;

/**
 * Generator for Piece objects in BlockAddiction. Icons are always selected
 * uniformly at random, and the Piece types are generated with the following
 * probabilities:
 * <ul>
 * <li>LPiece - 10%
 * <li>DiagonalPiece - 25%
 * <li>CornerPiece - 15%
 * <li>SnakePiece - 10%
 * <li>IPiece - 40%
 * </ul>
 * The initial position of each piece is based on its vertical size as well as
 * the width of the grid (given as an argument to getNext). The initial column
 * is always width/2 - 1. The initial row is: *
 * <ul>
 * <li>LPiece - row = -2
 * <li>DiagonalPiece - row = -1
 * <li>CornerPiece - row = -1
 * <li>SnakePiece - row = -1
 * <li>IPiece - row = -2
 * </ul>
 * 
 */
public class BasicGenerator implements Generator {
	private Random rand;

	/**
	 * Constructs a BasicGenerator that will use the given Random object as its
	 * source of randomness.
	 * 
	 * @param givenRandom instance of Random to use
	 */
	public BasicGenerator(Random givenRandom) {
		rand = givenRandom;
	}

	
	/**
	 * Creates a new piece object for the game (I, L, Diagonal, Corner, Snake). 
	 * Each piece has a certain percent chance of being created that is determined 
	 * by a random number. Specs on where each piece should start and probabilities 
	 * are shown at the beginning of this class.
	 * @param width - the width of the gameboard for which the piece is being created
	 * @return new piece
	 */
	@Override
	public Piece getNext(int width) {
		int col = width / 2 - 1;
		Piece newPiece;
		int randomNum = rand.nextInt(20) + 1;	//Gives numbers 1-20 (5% chance of getting each)
		
		if (randomNum <= 2) {					//covers numbers 1-2 (10% chance of L piece)
			Icon[] iconList = {randomIcon(), randomIcon(), randomIcon(), randomIcon()};	//Constructs a list of 4 icon objects to be used in the creation of an L piece
			newPiece = new LPiece(new Position(-2, col), iconList);
		} else if (randomNum <= 7) {			//covers numbers 3-7 (25% chance of Diagonal piece)
			Icon[] iconList = {randomIcon(), randomIcon()};	//Constructs a list of 2 icon objects to be used in the creation of a diagonal piece
			newPiece = new DiagonalPiece(new Position(-1, col), iconList);
		} else if (randomNum <= 10) {			//covers numbers 8-10 (15% chance of Corner piece)
			Icon[] iconList = {randomIcon(), randomIcon(), randomIcon()};	//Constructs a list of 3 icon objects to be used in the creation of a corner piece
			newPiece = new CornerPiece(new Position(-1, col), iconList);
		} else if (randomNum <=12) {			//covers numbers 11-12 (10% chance of Snake piece)
			Icon[] iconList = {randomIcon(), randomIcon(), randomIcon(), randomIcon()};	//Constructs a list of 4 icon objects to be used in the creation of a snake piece
			newPiece = new SnakePiece(new Position(-1, col), iconList);
		} else {								//covers numbers 13-20 (40% chance of I piece)
			Icon[] iconList = {randomIcon(), randomIcon(), randomIcon()};	//Constructs a list of 3 icon objects to be used in the creation of an I piece
			newPiece = new IPiece(new Position(-2, col), iconList);
		}
		
		return newPiece;
	}

	/**
	 * Returns one of the possible Icon types for this generator, selected at random.
	 * @return randomly selected Icon
	 */
	@Override
	public Icon randomIcon() {
		return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);
	}
}
