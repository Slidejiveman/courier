package courieremail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email validator using regular expressions.
 * 
 * @see https://stackoverflow.com/questions/8204680/java-regex-email
 *
 */
public class EmailValidator {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean validate(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		        return matcher.find();
		}
}
