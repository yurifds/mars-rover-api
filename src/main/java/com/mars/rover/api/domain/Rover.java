package com.mars.rover.api.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mars.rover.api.enums.OrientationEnum;

@Component
@Scope(scopeName="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rover {
	
	private OrientationEnum orientation;
	
	@Autowired
	private Coordinate coordinate;
	
	public Rover() {
		this.orientation = OrientationEnum.NORTH;
	}
	
	public Rover(OrientationEnum orientation, Coordinate coordinate) {
		super();
		this.orientation = orientation;
		this.coordinate = coordinate;
	}

	public void turnFor(String direction) {
		this.orientation = orientation.turnFor(direction);
	}
	
	public void moveOn() {
		switch (orientation) {
		case NORTH:
			coordinate.incY();
			break;
		case SOUTH:
			coordinate.decY();
			break;	
		case EAST:
			coordinate.incX();
			break;
		case WEST:
			coordinate.decX();
			break;	
		}
	}
		
	public OrientationEnum getOrientation() {
		return orientation;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
}