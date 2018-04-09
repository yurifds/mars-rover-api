package com.mars.rover.api.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mars.rover.api.command.CoordinateCommand;
import com.mars.rover.api.enums.CardinalDirection;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rover {

	@Autowired
	private Position position;

	private CardinalDirection cardinalDirection;
	private static final Map<Character, CoordinateCommand> coordinateCommands = new HashMap<Character, CoordinateCommand>();
	
	static {
		coordinateCommands.put('N', Position::forwardCoordinateY);
		coordinateCommands.put('S', Position::backwardCoordinateY);
		coordinateCommands.put('E', Position::forwardCoordinateX);
		coordinateCommands.put('W', Position::backwardCoordinateX);
	}

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

	public void goForward() {
		coordinateCommands.get(cardinalDirection.getPrefix()).apply(position);
	}

	public CardinalDirection getOrientation() {
		return cardinalDirection;
	}

	public Position getCoordinate() {
		return position;
	}
}