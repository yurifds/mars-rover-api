package com.mars.rover.api.factories;

import com.mars.rover.api.domain.Coordinate;

public class CoordinateFactory {
	
	public static Coordinate buildCoordinate(Integer x, Integer y) {
		return new Coordinate(x, y);
	}
}
