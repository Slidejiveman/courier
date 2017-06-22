package courierpd.other;

import java.util.Date;

public class DateParser {
	
	@SuppressWarnings("deprecation")
	public static String printDate(Date date) {
		String dateYear = String.valueOf((date.getYear()+1900));
		String dateMonth = String.valueOf((date.getMonth()+1));
		
		return dateMonth + "/" + date.getDate() + "/" + dateYear;
	}
	
	@SuppressWarnings("deprecation")
	public static String printTime(Date date) {
		
		String hourString = String.valueOf(date.getHours());
		String minuteString = String.valueOf(date.getMinutes());
		String amPmString = "AM";
		
		// Afternoon
		if (date.getHours() >= 12) {
			amPmString = "PM";
		}
		
		// Converting to 12 hour AM/PM time
		if (date.getHours() > 12) {
			hourString = String.valueOf(date.getHours() % 12);			
		}
		
		// Adding a leading 0 to the string
		if (date.getMinutes() < 10) {
			minuteString = "0" + minuteString;
		}
		
		return hourString + ":" + minuteString + " " + amPmString;
	}

}
