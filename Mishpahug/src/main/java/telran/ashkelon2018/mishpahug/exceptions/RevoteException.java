package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RevoteException extends RuntimeException {

	int code;
	String message;
	
	public RevoteException() {
		this.code = 409;
		this.message = "User has already voted for the event or can't vote for the event!";
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

}