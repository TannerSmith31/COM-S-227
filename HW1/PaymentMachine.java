package hw1;

/**
 * Class that initializes the payment machine and contains most methods used by the machine
 * @author Tanner Smith
 *
 */
public class PaymentMachine
{
	//instance variables
	private SimpleClock sc; //the payment machines simple clock
	private double totalPayments; //keeps track of how much money has been paid
	private boolean inProgress = false; //keeps track of whether there is a ticket in the machine
	private Ticket currentTicket; //the ticket currently in the machine
	private int paymentTime; //when payment was last made
	
	/**
	 * Constructor that creates another payment Machine using an clock given by user
	 * initializes total payments with 0.
	 * @param givenClock: the global clock that sets the payment machines clock
	 */
	public PaymentMachine(SimpleClock givenClock) {
		sc = givenClock;
		totalPayments = 0.0;
	}
	
	//Methods
	/**
	 * method that simulates you putting a ticket in the machine. Turns the "inProgress" variable to 
	 * true when a card is inserted and assigns the "currentTicket" in the machine with the inserted ticket
	 * @param t ticket inserted into the machine
	 */
	public void insertTicket(Ticket t) {
		if (inProgress == false) {
			currentTicket = t;
		}
		inProgress = true;
	}
	
	/**
	 * method to tell whether the machine is in use (has a ticket in it)
	 * @return whether or not the machine is in use
	 */
	public boolean inProgress() {
		return inProgress;
	}
	
	/**
	 * lets the user access the ticket that is currently in the machine
	 * @return the ticket currently in the machine
	 */
	public Ticket getCurrentTicket() {
		if (inProgress == true) {
			return currentTicket;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method that returns how much money you owe on the ticket you put in the machine
	 * NOTE: when you pay at the machine and then get rejected at the exit machine and come
	 * back, you only have to pay for the additional minutes between when you paid and arrived
	 * back at the payment machine (the price will be the difference in prices from, lets say
	 * 3 to 4 hours, and NOT as if you are starting over at 0 minutes again)
	 * @return payment due at next pay
	 */
	public double getPaymentDue() {
		if (inProgress == true) {
			int currentTime = sc.getTime();
			int startTime = currentTicket.getStartTime();
			int paymentTime = currentTicket.getPaymentTime();
			if (paymentTime == 0) {
				return ParkingRateUtil.calculateCost(currentTime-startTime);
			}
			else{
				double totalTimeCost = ParkingRateUtil.calculateCost(currentTime - startTime); //Amount due for the entire time you were in the parking garage
				double paidAlready = ParkingRateUtil.calculateCost(paymentTime - startTime); //Amount you have already paid the first time you went to the payment machine
				return totalTimeCost - paidAlready;//You only have to pay for the additional minutes from when you paid to when you returned to the payment machine after being rejected at the gate
			}
		}
		else {
			return 0.0;
		}
	}
	
	/**
	 * Method that adds up the total amount that you had to pay at the machine by calculating the cost
	 * of parking with the getPaymentDue method and then adding it to any previous payments made. Also
	 * stamps the payment time onto the inserted ticket.
	 */
	public void makePayment() {
		if (inProgress == true) {
			totalPayments += getPaymentDue();
			paymentTime = sc.getTime();
			currentTicket.setPaymentTime(paymentTime);
		}
	}
	
	/**
	 * ejects the ticket from the machine
	 */
	public void ejectTicket() {
		inProgress = false;
		currentTicket = null;
	}
	
	/**
	 * returns the total amount owed from parking
	 * @return total amount owed from parking
	 */
	public double getTotalPayments() {
		return totalPayments;
	}
	
}
