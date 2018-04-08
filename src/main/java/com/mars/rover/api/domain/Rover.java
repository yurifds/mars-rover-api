package com.mars.rover.api.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mars.rover.api.enums.CardinalDirection;

@Component
@Scope(scopeName="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rover {
	
	@Autowired
	private Position position;
	
	private CardinalDirection cardinalDirection;
	
	public Rover() {
		this.cardinalDirection = CardinalDirection.NORTH;
	}
	
	public Rover(CardinalDirection cardinalDirection, Position coordinate) {
		super();
		this.cardinalDirection = cardinalDirection;
		this.position = coordinate;
	}

	public void turnRight() {
		this.cardinalDirection = cardinalDirection.goRight();
	}
	
	public void turnLeft() {
		this.cardinalDirection = cardinalDirection.goLeft();
	}
	
	public void moveOn() {
		switch (cardinalDirection) {
		case NORTH:
			position.forwardCoordinateY();
			break;
		case SOUTH:
			position.backwardCoordinateY();
			break;	
		case EAST:
			position.forwardCoordinateX();
			break;
		case WEST:
			position.backwardCoordinateX();
			break;	
		}
	}
		
	public CardinalDirection getOrientation() {
		return cardinalDirection;
	}

	public Position getCoordinate() {
		return position;
	}
}