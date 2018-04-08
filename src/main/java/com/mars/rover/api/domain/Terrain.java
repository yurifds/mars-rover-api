package com.mars.rover.api.domain;

public class Terrain {
	
	private final int minLimitX = 0;
    private final int minLimitY = 0;
    private int maxLimitX;
    private int maxLimitY;
    
    
    public Terrain(int maxLimitX, int maxLimitY) {
    	this.maxLimitX = maxLimitX;
    	this.maxLimitY = maxLimitY;
    }

	public int getMinLimitX() {
		return minLimitX;
	}

	public int getMinLimitY() {
		return minLimitY;
	}

	public int getMaxLimitX() {
		return maxLimitX;
	}

	public int getMaxLimitY() {
		return maxLimitY;
	}
}
