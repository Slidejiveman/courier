package courierpd.other;

public class PhoneValidator {

		public static boolean validatePhoneNumber(String phoneNumber)
		{
			//Validates phone number with just the numbers
			if (phoneNumber.matches("\\d{10}")) return true;
			//Validates phone number if it includes -, ., or spaces between numbers
			else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
			//Validates phone number if it includes an extension link between 3 and 5 numbers
			else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
			//Validates phone number if it has the area code in parentheses
			else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
			//Returns false if the string provided does not match any of the above cases
			else return false;
		}
}
