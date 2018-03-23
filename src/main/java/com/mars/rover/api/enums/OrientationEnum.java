package com.mars.rover.api.enums;

public enum OrientationEnum {
	NORTH('N'),
	SOUTH('S'),
	EAST('E'),
	WEST('W');
	
	private Character prefix;

	private OrientationEnum(Character prefix) {
		this.prefix = prefix;
	}
	
	public OrientationEnum turnFor(String direction) {
		OrientationEnum orientation = this;
		switch (this) {
		case NORTH:
			orientation = fromNorthTo(direction);
			break;
		case SOUTH:
			orientation = fromSouthTo(direction);
			break;
		case EAST:
			orientation = fromEastTo(direction);
			break;
		case WEST:
			orientation = fromWestTo(direction);
			break;
		}
		return orientation;
	}

	private OrientationEnum fromNorthTo(String direction) {
		if(direction.equals("R")) return OrientationEnum.EAST;
		return OrientationEnum.WEST;
	}
	
	private OrientationEnum fromSouthTo(String direction) {
		if(direction.equals("R")) return OrientationEnum.WEST;
		return OrientationEnum.EAST;
	}

	private OrientationEnum fromEastTo(String direction) {
		if(direction.equals("R")) return OrientationEnum.SOUTH;
		return OrientationEnum.NORTH;
	}
	
	private OrientationEnum fromWestTo(String direction) {
		if(direction.equals("R")) return OrientationEnum.NORTH;
		return OrientationEnum.SOUTH;
	}
	
	public Character getPrefix() {
		return prefix;
	}
}
