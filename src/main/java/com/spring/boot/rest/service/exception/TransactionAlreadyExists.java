package com.spring.boot.rest.service.exception;

/**
 * A custom exception that extends {@link Exception}.
 * @author Simon Njenga
 * @version 0.1
 */
public class TransactionAlreadyExists extends Exception {

    private static final long serialVersionUID = 514455541191696531L;

    /**
	 * Constructor 
	 * @param message message to throw
	 */
	public TransactionAlreadyExists(final String message) {
        super(message);
    }
}
