package com.mars.rover.api.dtos;

public class RoverDto {
	private int coordinateX;
	private int coordinateY;
	private String direction;
	private String currentPosition;
	
	public int getCoordinateX() {
		return coordinateX;
	}
	
	public void setCoordinateX(Integer x) {
		this.coordinateX = x;
	}
	
	public int getCoordinateY() {
		return coordinateY;
	}
	
	public void setCoordinateY(int y) {
		this.coordinateY = y;
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
