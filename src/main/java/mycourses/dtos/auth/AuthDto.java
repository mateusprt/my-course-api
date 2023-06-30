package mycourses.dtos.auth;

public class AuthDto {
	
	private String message;

	public AuthDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
