package mycourses.utils;

public class EmailUtils {
	
	private static String EMAIL_REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" 
	        + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
	
	public static boolean validate(String email) {
		return email.matches(EMAIL_REGEX_PATTERN);
	}

}
