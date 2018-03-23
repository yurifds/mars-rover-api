package com.mars.rover.api.validators;


public interface PathParamValidator {
	
	/**
	 * Validates the command character
	 * @param param
	 * @return boolean
	 */
	boolean isValid(String param);
}
