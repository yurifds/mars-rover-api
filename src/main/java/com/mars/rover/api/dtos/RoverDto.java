package com.mars.rover.api.dtos;

public class RoverDto {
	private Integer x;
	private Integer y;
	private String direction;
	private String currentPosition;
	
	public Integer getX() {
		return x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
}
