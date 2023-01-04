package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Generator for secret words or phrases for word-guessing games. A PhraseSelector chooses a line 
 * randomly from a file specified in the constructor. This version reads the file just once in the 
 * constructor and stores the words in an ArrayList.
 * @author Tanner Smith
 *
 */
public class PhraseSelector {
	private File file;						//stores a file to be used by the phraseSelector
	private ArrayList<String> fileLines;	//stores a list of strings to be used in a hangman game
	
	
	/**
	 * Constructor that creates a new phrase Selector object containing an array of lines from a file
	 * that has been given as a parameter.
	 * @param givenFilename: a file given by the user that will be used to choose words from
	 * @throws FileNotFoundException
	 */
	public PhraseSelector(String givenFilename) throws FileNotFoundException {
		file = new File(givenFilename);
		Scanner fileScanner = new Scanner(file);
		
		//constructing an arrayList containing each line of the file as a string
		fileLines = new ArrayList<String>();
		while (fileScanner.hasNextLine()) {
			fileLines.add(fileScanner.nextLine());
		}
		fileScanner.close();	
	}

	
	/**
	 * returns a single line from the file within the phrase selector that will be used in the game of hangman
	 * @param rand: a new random object given by the user that is used to randomize the phrase in the hangman
	 * @return gameword: the word or phrase that will be used as the secret word in the game of hangman
	 * @throws FileNotFoundException
	 */
	public String selectWord(Random rand) throws FileNotFoundException {
		int randomIndex = rand.nextInt(fileLines.size());	//getting a random number within the range of the fileLines length
		String gameWord = fileLines.get(randomIndex);
		return gameWord;
	}
	
}
