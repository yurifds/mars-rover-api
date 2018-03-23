package com.mars.rover.api.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Coordinate {
	
	private Integer x;
	private Integer y;
	
	public Coordinate() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public void incX() {
		this.x++;
	}
	
	public void decX() {
		this.x--;
	}
	
	public void incY() {
		this.y++;
	}
	
	public void decY() {
		this.y--;
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}
}
