package hw2;

import java.util.ArrayList;
/**
 * Class representing a game of hangman. This class encapsulates all aspects of the game state, 
 * including the secret word or phrase, the letters guessed so far, and the number of wrong guesses. 
 * A hangman game can be constructed with a specified maximum number of wrong guesses which defaults 
 * to DEFAULT_MAX_WRONG_GUESSES. The secret word or phrase is represented as an array of HideableChar 
 * and may contain whitespace and arbitrary punctuation characters. Clients of this code decide how to 
 * represent the "hidden" characters to the user. Non-alphabetic characters are normally not hidden.
 * @author Tanner Smith
 */


//import plotter.Polyline;  //this line is giving me issues

public class Game {

	public static final int DEFAULT_MAX_WRONG_GUESSES = 7;	//default number of max wrong guesses for a game (if not specified).
	private int maxGuesses;							//a variable that holds the max number of guesses for a game.
	private String secretWord;						//a variable that holds the secret word or phrase
	private String guessedLetters = "";				//a string to contain all guessed letters
	private int wrongGuesses; 						//counts how many guesses have been wrong
	private HideableChar[] hideableCharArray;		//holds all of the hideable characters for each round
	private boolean gameWon = false; 				//keeps track of whether the game has been won or not
	private boolean firstRound = true;				//tells if we are on the first round so that the gameOver method works
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";	//a string variable to contain any punctuation we don't want hidden

	
	/**
	 * Constructs a hangman game using the given word as the secret word and the default maximum number of wrong guesses.
	 * @param word : the word or phrase that will be used as the secret word for this game of hangman
	 */
	public Game(String word) {
		maxGuesses = DEFAULT_MAX_WRONG_GUESSES;
		secretWord = word;
		wrongGuesses = 0;
	}
	
	
	/**
	 * Constructs a hangman game using the given word as the secret word and the given value as the maximum number of wrong guesses.
	 * @param word : the word or phrase that will be used as the secret word for this game
	 * @param maxGuesses : the maximum number of times a player can guess a wrong letter before losing
	 */
	public Game(String word, int maxGuesses) {
		this.maxGuesses = maxGuesses;
		secretWord = word;
		wrongGuesses = 0;
	}

	
	/**
	 * Returns the maximum number of wrong guesses for this game 
	 * @return : maximum number of wrong guesses for this game
	 */
	public int getMaxGuesses() {
		return maxGuesses;
	}

	
	/**
	 * Determines whether this game is over
	 * @return whether the game is over
	 */
	public boolean gameOver() {
		if (wrongGuesses == maxGuesses) {
			return true;		//you lose
		} else if (firstRound == true) {	//keeps the method from trying to call the HideableCharArray before its been created
			firstRound = false;
			return false;
		} else {
			gameWon = true;
			for(int i = 0; i < secretWord.length(); i++) {
				//the following else only executes if one of the hidden characters was not guessed and was a letter
				if (!(guessedLetters.contains(hideableCharArray[i].getHiddenChar())) && (ALPHABET.contains(hideableCharArray[i].getHiddenChar()))) {
					gameWon = false;
					return false;
				}
			}
			gameWon = true;
			return true;
		}
	}

	
	/**
	 * Returns the number of wrong guesses made so far by the player.
	 * @return : the number of wrong guesses made so far by the player
	 */
	public int numWrongGuesses() {
		return wrongGuesses;
	}

	
	/**
	 * Determines whether the player has guessed all the letters in the secret word.
	 * @return : whether the player has won or not
	 */
	public boolean won() {
		return gameWon;
	}

	
	/**
	 * Returns a string containing all the letters guessed so far by the player, without duplicates.
	 * @return : a string containing all the letters guessed so far by the player (no duplicates).
	 */
	public String lettersGuessed() {
		return guessedLetters;
	}

	/**
	 * Returns a sequence of HideableChar representing the secret word or phrase.
	 * @return : a sequence of HideableChar representing the secret word or phrase.
	 */
	public HideableChar[] getDisplayedWord() {
		int secretWordLength = secretWord.length();
		hideableCharArray = new HideableChar[secretWordLength];
		for (int i = 0; i < secretWord.length(); i++) {
			hideableCharArray[i] = new HideableChar(secretWord.charAt(i));	//this creates an array of hideable characters for each letter in the secret word
			//UNHIDES ANY CORRECT LETTERS THAT HAVE BEEN GUESSED
			if (guessedLetters.contains(hideableCharArray[i].getHiddenChar())) {
				hideableCharArray[i].unHide();
			}
		}
		return hideableCharArray;
	}

	
	/**
	 * Returns the complete secret word or phrase as a string (no matter if it is hidden or not)
	 * @return complete secret word/phrase as a string
	 */
	public String getSecretWord() {
		// TODO: Returns the complete secret word or phrase as a string.
		return secretWord;
	}

	
	/**
	 * Invoked by the player to guess a letter. Return true if the letter is in the secret word, return false otherwise
	 * @param ch : the character that the character is guessing
	 * @return : true if letter is in secret word, false if not.
	 */
	public boolean guessLetter(char ch) {
		String GuessAsString = "" + ch;
		if (!guessedLetters.contains(GuessAsString)) {
			guessedLetters += ch;
		}
		if (secretWord.contains(GuessAsString)) {
			return true; //Letters are uncovered inside the getDisplayedWord method @ line 70-71 rather than here
		} else {
			wrongGuesses++;
			return false;
		}
	}
}
