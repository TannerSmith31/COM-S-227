package hw2;

/**
 * Class representing a hidden character for a word-guessing game. Each instance encapsulates one character, 
 * which may have a status of "hidden" or "not hidden". When in the hidden state, getDisplayedChar returns null; 
 * if not hidden, getDisplayedChar returns the encapsulated character as a one-character string.
 * @author Tanner Smith
 *
 */

public class HideableChar {
	private boolean isHidden;		//variable to store whether or not the HideableChar object is hidden or not
	private char character;			//variable to store the character specific to that hideableChar object
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";	//a string variable to contain any punctuation we don't want hidden
	
	
	/**
	 * Constructs a HideableChar object and sets it as hidden if it is a letter and unhidden otherwise.
	 * @param ch : character that will be assigned to the HideableChar object (the letter/punctuation the object will be)
	 */
	public HideableChar(char ch) {
		character = ch;
		String characterAsString = "" + character;
		if (ALPHABET.contains(characterAsString)) {	//makes sure that punctuation and spaces are not hidden
			isHidden = true;
		} else {
			isHidden = false;
		}
	}

	/**
	 * Determines whether the HiddenChar object it is being called on is in the hidden state
	 * @return : whether or not the object is hidden
	 */
	public boolean isHidden() {
		return isHidden;
	}

	/**
	 * Sets this object's state to hidden.
	 */
	public void hide() {
		isHidden = true;
	}

	/**
	 * Sets this object's state to not hidden.
	 */
	public void unHide() {
		isHidden = false;
	}

	
	/**
	 * Determines whether the given character is equal to the character stored in this object
	 * @param ch : the character that you want to compare with the HideableChar object
	 * @return boolean value telling if the HideableChar object and given character match
	 */
	public boolean matches(char ch) {
		if (ch == character) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * Returns null if this object is in the hidden state, otherwise returns a one-character string consisting of the character stored in this object.
	 * @return the character in the object if it is unhidden
	 */
	public String getDisplayedChar() {
		String stringChar = "";
		if (isHidden) {
			return null;
		}
		else {
			stringChar = stringChar + character;
			return stringChar;
		}
	}

	/**
	 * Returns a one-character string consisting of the character stored in this object
	 * @return the character in the object no matter if its hidden or not
	 */
	public String getHiddenChar() {
		// TODO: Returns a one-character string consisting of the character stored in this object.
		String stringChar = "";
		stringChar = stringChar + character;
		return stringChar;
	}
}
