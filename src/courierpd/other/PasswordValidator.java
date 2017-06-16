package courierpd.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
	
	public static final Pattern PasswordValidator = 
		    Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})", Pattern.CASE_INSENSITIVE);

		public static boolean validate(String passwordStr) {
		        Matcher matcher = PasswordValidator .matcher(passwordStr);
		        return matcher.find();
		}
	}

