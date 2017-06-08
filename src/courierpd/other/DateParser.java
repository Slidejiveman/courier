package courierpd.other;

import java.util.Date;

public class DateParser {
	@SuppressWarnings("deprecation")
	public static Date parseDatabaseDate(Date date){
		Integer Year = date.getYear();
		Integer Month = date.getMonth();
		Integer Day =date.getDate();
		
		return new Date(Year,Month,Day);
	}

}
