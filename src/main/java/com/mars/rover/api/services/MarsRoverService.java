package com.mars.rover.api.services;

import com.mars.rover.api.domain.Rover;

public interface MarsRoverService {
	
	/**
	 * Returns a Rover with the current position updated
	 * @param commands
	 * @return Rover
	 */
	public Rover excuteCommands(String commands);
}
