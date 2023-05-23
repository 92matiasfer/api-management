package uy.com.stronghold.apimanagement.exceptions;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;


public class ValidationException extends IOException implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9157339218822095743L;

	@Autowired
	protected ErroresInterface error;
	
	Object errorOn;
	
	public ErroresInterface geterror() {
		return error;
	}

	public ValidationException(ErroresInterface error) {
		super(error.getMensajeError());
		this.error = error;
	}

	public ValidationException(String message, Throwable cause, ErroresInterface error) {
		super(message, cause);
		this.error = error;
	}

	public ValidationException(String message, ErroresInterface error) {
		super(message);
		this.error = error;
	}

	public ValidationException(ErroresInterface error, Throwable cause) {
		super(error.getMensajeError(), cause);
		this.error = error;
	}

	public Object getErrorOn() {
		return errorOn;
	}
	
	public ValidationException() {
		super();
	}

}
