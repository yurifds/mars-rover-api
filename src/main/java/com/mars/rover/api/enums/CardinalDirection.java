package com.mars.rover.api.enums;

public enum CardinalDirection {
	NORTH('N'),
	SOUTH('S'),
	EAST('E'),
	WEST('W');
	
	private char prefix;

	private CardinalDirection(char prefix) {
		this.prefix = prefix;
	}
	
	public CardinalDirection goRight() {
		CardinalDirection orientation = this;
		switch (this) {
		case NORTH:
			orientation = CardinalDirection.EAST;
			break;
		case SOUTH:
			orientation = CardinalDirection.WEST;
			break;
		case EAST:
			orientation = CardinalDirection.SOUTH;
			break;
		case WEST:
			orientation = CardinalDirection.NORTH;
			break;
		}
		return orientation;
	}
	
	public CardinalDirection goLeft() {
		CardinalDirection orientation = this;
		switch (this) {
		case NORTH:
			orientation = CardinalDirection.WEST;
			break;
		case SOUTH:
			orientation = CardinalDirection.EAST;
			break;
		case EAST:
			orientation = CardinalDirection.NORTH;
			break;
		case WEST:
			orientation = CardinalDirection.SOUTH;
			break;
		}
		return orientation;
	}
	
	public char getPrefix() {
		return prefix;
	}
}
