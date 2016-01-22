package com.spring.boot.rest.service.exception;

/**
 * A custom exception that extends {@link Exception}.
 *
 * @author Simon Njenga
 * @version 0.1
 */
public class TransactionAlreadyExists extends Exception {

    private static final long serialVersionUID = 514455541191696531L;

    /**
     * Constructor - No detail message to show when exception is thrown
     */
    public TransactionAlreadyExists() {
        super();
    }

    /**
     * Constructor 
     * @param message message to throw
     */
    public TransactionAlreadyExists(String message) {
        super(message);
    }

    /**
     * Constructor 
     * @param throwable throwable to throw
     */
    public TransactionAlreadyExists(Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructor 
     * @param message message to throw
     * @param throwable throwable to throw
     */
    public TransactionAlreadyExists(String message, Throwable throwable) {
        super(message, throwable);
    }
}
