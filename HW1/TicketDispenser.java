package hw1;
/**
 * Machine that gives you a ticket
 * @author Tanner Smith
 *
 */
public class TicketDispenser
{
	private SimpleClock sc; //instance variable
	
	/**
	 * Assigns the internal clock of the TicketDispenser to the global given clock
	 * @param givenClock: Clock given while testing
	 */
	public TicketDispenser(SimpleClock givenClock) {	
		sc = givenClock;
	}
	
	/**
	 * constructs a new ticket, stamps it with the time it was created, and initializes payment time as 0
	 * @return new ticket
	 */
	public Ticket takeTicket() {
		Ticket t1 = new Ticket(sc.getTime());
		t1.setPaymentTime(0);
		return t1;
	}
	
}
