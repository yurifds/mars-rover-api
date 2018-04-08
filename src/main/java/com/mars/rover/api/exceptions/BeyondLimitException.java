package com.mars.rover.api.exceptions;

public class BeyondLimitException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeyondLimitException() {
        super("This action is leaving the rover beyond of terrain limits.");
    }

}
