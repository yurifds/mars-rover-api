package com.mars.rover.api.factories;

import com.mars.rover.api.domain.Position;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.enums.CardinalDirection;

public class RoverFactory {
	
	public static Rover buildRover(Integer x, Integer y, CardinalDirection orientation) {
		return new Rover(orientation, new Position(x, y));
	}
}
