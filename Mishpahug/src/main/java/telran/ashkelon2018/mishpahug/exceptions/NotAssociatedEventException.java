package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotAssociatedEventException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int code;
	String message;

	public NotAssociatedEventException() {
		this.code = 409;
		this.message = "User is already invited to the event or is not subscribed to the event!";

	}
}
