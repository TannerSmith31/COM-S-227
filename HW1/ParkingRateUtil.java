package hw1;

/**
 * A utility class for the parking garage that contains useful methods
 * @author Tanner Smith
 */
public class ParkingRateUtil{

	public static final int EXIT_TIME_LIMIT = 15; //how many minutes you have to exit after paying
	
	private ParkingRateUtil() {
		//Is anything supposed to go in here? we were told to have this constructor, but for what?
	}
	
	/**
	 * calculates the total payment due after being parked so many hours
	 * @param minutes: the number of minutes you are paying for
	 * @return payment due
	 */
	public static double calculateCost(int minutes){
		double payment = 0;
		double hours = minutes/60.0;
		if (minutes > 1440) {
			payment = 13.00 * (minutes/1440);
			minutes = minutes % 1440;
			hours = minutes / 60.0;
		}
		
		if (hours <= 0.5){
			payment += 1.00;
		}
		else if (hours <= 1){
			payment += 2.00;
		}
		else if (hours <= 2){
			payment += 3.50;
		}
		else if (hours <= 3){
			payment += 5.00;
		}
		else if (hours <= 4){
			payment += 6.50;
		}
		else if (hours <= 5){
			payment += 8.00;
		}
		else if (hours <= 6){
			payment += 9.25;
		}
		else if (hours <= 7){
			payment += 10.50;
		}
		else if (hours <= 8){
			payment += 11.75;
		}
		else {
			payment += 13.00;
		}
		return payment;
	}
}
