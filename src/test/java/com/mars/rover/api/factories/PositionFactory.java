package com.mars.rover.api.factories;

import com.mars.rover.api.domain.Position;

public class PositionFactory {
	
	public static Position buildPosition(Integer x, Integer y) {
		return new Position(x, y);
	}
}
