package com.spring.boot.rest.service.exception;

/**
 * A custom exception that extends {@link Exception}.
 * @author Simon Njenga
 * @version 0.1
 */
public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -172828095845683598L;

	/**
	 * Constructor 
	 * @param message message to throw
	 */
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
