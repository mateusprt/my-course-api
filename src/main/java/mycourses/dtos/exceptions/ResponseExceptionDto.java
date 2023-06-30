package mycourses.dtos.exceptions;

import java.util.Date;
import java.util.List;

public class ResponseExceptionDto {
	
	private Date timestamp;
	private List<String> messages;
	private String details;
	
	public ResponseExceptionDto(List<String> messages, String details) {
		this.timestamp = new Date();
		this.messages = messages;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	

}
