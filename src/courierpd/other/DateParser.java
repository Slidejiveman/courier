package courierpd.other;

import java.util.Calendar;
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

	public static String subtractDates(Date date1, int numberDays) {
		int dayDifference, monthDifference, yearDifference;
		String dateYear, dateMonth;
		// see if the subtraction of the number days, 7 or 30, 
		// will rollover to a negative number
		dayDifference = date1.getDate() - numberDays;
		monthDifference = date1.getMonth();
		yearDifference = date1.getYear();
		
		// if the number is negative, then add it to the number of days
		// in the given month.
		if (dayDifference <= 0) {
			if (date1.getMonth() == 0 || date1.getMonth() == 2 || date1.getMonth() == 4 ||
					date1.getMonth() == 6 || date1.getMonth() == 7 || date1.getMonth() == 9
					|| date1.getMonth() == 11) {
				dayDifference += 31; 
			} else if (date1.getMonth() == 1) {
				dayDifference += 28;
			} else {
				dayDifference += 30;
			}
			monthDifference -= 1;
			
			// if the month is January, make it December.
			if (monthDifference < 0) {
				monthDifference = 11;
				
				// decrement the year as well
				yearDifference -= 1;
			}
		}
		dateYear = String.valueOf(yearDifference + 1900);
		dateMonth = String.valueOf(dayDifference + 1);
		// return the date formatted as a string
		return dateMonth + "/" + dayDifference + "/" + dateYear;
		
	}
}
