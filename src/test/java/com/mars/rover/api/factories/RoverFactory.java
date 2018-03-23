package com.mars.rover.api.factories;

import com.mars.rover.api.domain.Coordinate;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.enums.OrientationEnum;

public class RoverFactory {
	
	public static Rover buildRover(Integer x, Integer y, OrientationEnum orientation) {
		return new Rover(orientation, new Coordinate(x, y));
	}
}
