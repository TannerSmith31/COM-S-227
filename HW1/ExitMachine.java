package hw1;
/**
 * Machine that takes the human's ticket and sees if they are worthy of leaving the parking garage.
 * @author Tanner Smith
 *
 */
public class ExitMachine
{
	//Instance Variables
	private SimpleClock sc; //The Exit Machine's own internal clock
	private int successfulExits = 0; //Keeps track of how many exits have been successful
	
	/**
	 * Constructor that constructs a new ExitMachine that uses the given clock
	 * @param givenClock: the global clock used to set the clocks of each machine
	 */
	public ExitMachine(SimpleClock givenClock) {
		sc = givenClock;
	}
	
	/**
	 * Simulates putting the ticket into the Exit Machine
	 * @param t: the ticket given to the machine
	 * @return: whether or not the gate will open (True = Open, False = Closed)
	 */
	public boolean insertTicket(Ticket t) {
		if ((sc.getTime() - t.getPaymentTime())<15 && t.getPaymentTime() > 0) {
			successfulExits += 1;
					return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Method that lets the user get the number of successful exits
	 * @return: number of successful exits
	 */
	public int getExitCount() {
		return successfulExits;
	}
}
