package com.mars.rover.api.exceptions;

public class InvalidCommandException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCommandException(char command) {
        super("Invalid Command: " + command);
    }
}
