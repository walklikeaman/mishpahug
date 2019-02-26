package telran.ashkelon2018.mishpahug.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusyDateException extends RuntimeException {

	int code;
	String message;
	
	public BusyDateException() {
		this.code = 409;
		this.message= "This user has already created the event on this date and time!";
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

}