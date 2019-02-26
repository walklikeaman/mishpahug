package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class UserIsNotAssociatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int code;
	String message;
	
	public UserIsNotAssociatedException(int code, String message) {
		this.code = 402;
		this.message = "User is not associated with the event!";
	}
	
}
