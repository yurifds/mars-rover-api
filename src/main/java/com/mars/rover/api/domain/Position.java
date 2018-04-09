package com.mars.rover.api.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mars.rover.api.exceptions.BeyondLimitException;

@Component
@Scope(scopeName="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Position {
	
	private int coordinateX;
	private int coordinateY;
	
	private Terrain terrain;
	
	public Position() {
		this.coordinateX = 0;
		this.coordinateY = 0;
		this.terrain = new Terrain(5, 5);
	}
	
	public Position(int x, int y) {
		this.coordinateX = x;
		this.coordinateY = y;
		this.terrain = new Terrain(5, 5);
	}

	public void forwardCoordinateX() {
		if(++coordinateX > terrain.getMaxLimitX())
			throw new BeyondLimitException();
	}
	
	public void backwardCoordinateX() {
		if(--coordinateX < terrain.getMinLimitX())
			throw new BeyondLimitException();
	}
	
	public void forwardCoordinateY() {
		if(++coordinateY > terrain.getMaxLimitY())
			throw new BeyondLimitException();
	}
	
	public void backwardCoordinateY() {
		if(--coordinateY < terrain.getMinLimitY())
			throw new BeyondLimitException();
	}
	
	public int getCoordinateX() {
		return coordinateX;
	}
	
	public int getCoordinateY() {
		return coordinateY;
	}
}
