package courierpd.other;

import java.util.Date;

public class DateParser {
	
	public static String printDate(Date date) {
		
		return date.getMonth() + "/" + date.getDate() + "/" + date.getYear();
	}
	
	public static String printTime(Date date) {
		
		String hourString = "";
		String amPmString = "AM";
		
		// Afternoon
		if (date.getHours() >= 12) {
			amPmString = "PM";
		}
		
		// Converting to 12 hour AM/PM time
		if (date.getHours() > 12) {
			hourString = String.valueOf(date.getHours() % 12);			
		}
		
		return hourString + ":" + date.getMinutes() + " " + amPmString;
	}

}
