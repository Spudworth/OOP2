

public class OpeningHours{
	
	private String weekdays = "11am - 6pm";
	private String sunday = "12pm - 6pm";

	public String hoursToString(){
		
		String message = String.format("Monday: %s\nTuesday: %s\nWednesday: %s\nThursday: %s\nFriday: %s\nSaturday: %s\nSunday: %s"
										,weekdays ,weekdays ,weekdays ,weekdays ,weekdays ,weekdays ,sunday);
										
		return message;
	}	
}