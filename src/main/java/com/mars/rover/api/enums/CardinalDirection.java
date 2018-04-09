package com.mars.rover.api.enums;

import java.util.HashMap;
import java.util.Map;


public enum CardinalDirection {
	NORTH('N'),
	SOUTH('S'),
	EAST('E'),
	WEST('W');
	
	private char prefix;
	
	private static final Map<Character, CardinalDirection> toRight = new HashMap<Character, CardinalDirection>();
	private static final Map<Character, CardinalDirection> toLeft = new HashMap<Character, CardinalDirection>();
	
	static {
		toRight.put('N', CardinalDirection.EAST);
		toRight.put('S', CardinalDirection.WEST);
		toRight.put('E', CardinalDirection.SOUTH);
		toRight.put('W', CardinalDirection.NORTH);
		
		toLeft.put('N', CardinalDirection.WEST);
		toLeft.put('S', CardinalDirection.EAST);
		toLeft.put('E', CardinalDirection.NORTH);
		toLeft.put('W', CardinalDirection.SOUTH);
	}
	
	private CardinalDirection(char prefix) {
		this.prefix = prefix;
	}
	
	public CardinalDirection goRight() {		
		return toRight.get(this.prefix);
	}
	
	public CardinalDirection goLeft() {
		return toLeft.get(this.prefix);
	}
	
	public char getPrefix() {
		return prefix;
	}
}
