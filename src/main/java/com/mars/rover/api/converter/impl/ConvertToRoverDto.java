package com.mars.rover.api.converter.impl;

import org.springframework.stereotype.Component;

import com.mars.rover.api.converter.ConvertToDto;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.dtos.RoverDto;

@Component
public class ConvertToRoverDto implements ConvertToDto<RoverDto, Rover>{
	
	/**
	 * Convert a Rover to RoverDto.
	 * 
	 * @param rover
	 * @return roverDto
	 */
	@Override
	public RoverDto convert(Rover rover) {
		RoverDto roverDto = new RoverDto();
		roverDto.setCoordinateX(rover.getCoordinate().getCoordinateX());
		roverDto.setCoordinateY(rover.getCoordinate().getCoordinateY());
		roverDto.setDirection(rover.getOrientation().name());
		String currentState = "(" + rover.getCoordinate().getCoordinateX() + ", " 
								  + rover.getCoordinate().getCoordinateY() + ", "
							      + rover.getOrientation().getPrefix() + ")";
		roverDto.setCurrentPosition(currentState);
		return roverDto;
	}
}
