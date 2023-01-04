package hw2;
/**
 * A class that tests the HideableChar class and was a pain in the a$$ to make.
 * @author Tanner Smith
 */
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;

public class HideableCharTest
{
	private HideableChar c1;
	private HideableChar c2;
	private HideableChar c3;
	private HideableChar c4;

	@Before
	public void setup() {
		c1 = new HideableChar('A');
		c2 = new HideableChar('%');
		c3 = new HideableChar('\\');
		c4 = new HideableChar('b');
	}
//*********************************************************************************TEST Matches
	@Test
	public void testMatches1() {
		assertTrue("HideableChar object should contain the letter it was initialized with (expected <False>)", c1.matches('A'));
	}

	@Test
		public void testMatches2() {
		assertTrue("HideableChar object should contain the letter it was initialized with (expected <True>)", c2.matches('%'));
	}

	@Test
	public void testMatches3() {
		assertFalse("<A> should not match <B> because they are different characters", c1.matches('B'));
	}

	@Test
	public void testMatches4() {
		assertFalse("<%> should not match <$> because they are different characters", c2.matches('$'));
	}

	@Test
	public void testMatches5() {
		assertFalse("<A> should not match <a> because they have cases", c1.matches('a'));
	}
//*************************************************************************************TEST Hide UnHide (& isHidden)
	@Test
	public void testHide1() {
		String msg = "Error - initial state should be not hidden. ";
		assertFalse(msg, c2.isHidden());

		c2.hide();
		assertTrue("calling the hide() method on a hideableChar should change its state to hidden", c2.isHidden());
	}

	@Test
	public void testHide2() {
		String msg = "Error - initial value should be \"%\". ";
		assertEquals(msg, "%", c2.getDisplayedChar());

		c2.hide();
	  	assertEquals("calling the hide() method on a hideableChar should make getDisplayedChar() return null", null, c2.getDisplayedChar());
	 }
	
	@Test
	public void testHide3() {
		String msg1 = "Error - getDisplayedChar should return %";
		String msg2 = "Error - c2 should be hidden";
		c2.hide();
		c2.unHide();
	  	assertEquals(msg1, "%", c2.getDisplayedChar());
	  	assertEquals(msg2, false, c2.isHidden());
	 }
	
	@Test
	public void testHide4() {
	  	c3.hide();
	  	assertEquals("should return <null> because object is hidden", null, c3.getDisplayedChar());
	  	assertEquals("object should be hidden", true, c3.isHidden());
	  	
	}
	
	@Test
	public void testHide5() {
		c1.hide();
		c1.hide();
		c1.unHide();
	  	assertEquals("should return A", "A", c1.getDisplayedChar());
	  	assertFalse("calling unHide on a HideableObject should unhide the object", c1.isHidden());
	 }
	
	@Test
	public void testHide6() {	//seeing if hide function changes other objects
		c1.hide();
	  	assertFalse("Hiding c1 should not affect state of c2", c2.isHidden());
	 }
	
	
//*************************************************************************************TEST isHidden
	@Test
	public void testIsHidden()
	{
		HideableChar c = new HideableChar('a');
		assertTrue("new letters should be initialized as hidden",c.isHidden());
	}
	
	@Test
	public void testIsHidden2()
	{
		HideableChar c = new HideableChar('$');
		assertFalse("new punctuation should be initialized as not hidden", c.isHidden());
	}
	
	@Test
	public void testIsHidden3()
	{
		HideableChar c = new HideableChar('a');
		c.unHide();
		assertFalse("letters that have been unhidden should not be hidden", c.isHidden());
	}
	
	@Test
	public void testIsHidden4()
	{
		HideableChar c = new HideableChar(' ');
		c.hide();
		assertTrue("punctuation that has been hidden should be hidden", c.isHidden());
	}
	
	@Test
	public void testIsHidden5()
	{
		HideableChar c = new HideableChar(' ');
		assertFalse("Spaces should be initialized as not hidden", c.isHidden());
	}
//***************************************************************************************TEST GetDisplayed
	@Test
	public void testGetDisplayed1()
	{
		assertEquals("calling getDisplayedChar on hidden objects should return null", null, c1.getDisplayedChar());
	}
	
	@Test
	public void testGetDisplayed2()
	{
		assertEquals("calling getDisplayedChar on unhidden objects should return the objects character", "%", c2.getDisplayedChar());
	}
	
	@Test
	public void testGetDisplayed3()
	{
		assertEquals("calling getDisplayedChar on unhidden objects should return the objects character", "\\", c3.getDisplayedChar());
	}
	
	@Test
	public void testGetDisplayed4()
	{
		assertEquals("calling getDisplayedChar on hidden objects should return null", null, c4.getDisplayedChar());
	}
	
	@Test
	public void testGetDisplayed5()
	{
		c4.unHide();
		assertEquals("calling getDisplayedChar on unhidden objects should return the objects character", "b", c4.getDisplayedChar());
	}
	
	@Test
	public void testGetDisplayed6()
	{
		c3.hide();
		assertEquals("calling getDisplayedChar on hidden objects should return null", null, c4.getDisplayedChar());
	}
	
//***************************************************************************************TEST GetHidden
	@Test
	public void testGetHidden1()
	{
		assertEquals("calling GetHidden should return the ojects character. Expected \"A\".", "A", c1.getHiddenChar());
	}
	
	@Test
	public void testGetHidden2()
	{
		assertEquals("calling GetHidden should return the ojects character. Expected \"%\".", "%", c2.getHiddenChar());
	}
	
	@Test
	public void testGetHidden3()
	{
		assertEquals("calling GetHidden should return the ojects character. Expected \"\\\"","\\", c3.getHiddenChar());
	}
	
	@Test
	public void testGetHidden4()
	{
		assertEquals("calling GetHidden should return the ojects character. Expected \"b\".", "b", c4.getHiddenChar());
	}
	
	@Test
	public void testGetHidden5()
	{
		c4.unHide();
		assertEquals("calling GetHidden should return the ojects character. Expected \"b\".", "b", c4.getHiddenChar());
	}
	
	@Test
	public void testGetHidden6()
	{
		c3.hide();
		assertEquals("calling GetHidden should return the ojects character. Expected \"%\".", "b", c4.getHiddenChar());
	}
	
}
