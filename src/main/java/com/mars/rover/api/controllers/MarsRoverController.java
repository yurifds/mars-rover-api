package com.mars.rover.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.dtos.RoverDto;
import com.mars.rover.api.response.Response;
import com.mars.rover.api.services.MarsRoverService;
import com.mars.rover.api.validators.PathParamValidator;
import com.mars.rover.api.validators.TerrainLimitValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("mars-rover/api/v1")
@Api(tags = {"MarsRover"})
public class MarsRoverController {

	@Autowired
	MarsRoverService roverService;

	@Autowired
	PathParamValidator pathParamValidator;
	
	@Autowired
	TerrainLimitValidator terrainLimitValidator;

	
	/**
	 * Sends commands to Rover on Mars.
	 * 
	 * @param command
	 * @return ResponseEntity<Response<RoverDto>>
	 */
	@PostMapping(value = "/{command}")
	@ApiOperation(value = "Sends commands to Rover on Mars", notes = "Returns a Rover with the current location.")
	public ResponseEntity<Response<RoverDto>> excuteCommands(@PathVariable("command") String command) {
		Response<RoverDto> response = new Response<RoverDto>();
		if (!pathParamValidator.isValid(command)) {
			response.getErrors().add("Invalid Command: " + command);
			return ResponseEntity.badRequest().body(response);
		}
		
		Rover rover = roverService.excuteCommands(command);
		
		if (!terrainLimitValidator.isValid(rover.getCoordinate())) {
			response.getErrors().add("Invalid Position for the following commands: " + command);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(convertToRoverDto(rover));
		
		return ResponseEntity.ok(response);
	}

	/**
	 * Return the dto with the Rover current location.
	 * 
	 * @param rover
	 * @return roverDto
	 */
	private RoverDto convertToRoverDto(Rover rover) {
		RoverDto roverDto = new RoverDto();
		roverDto.setX(rover.getCoordinate().getX());
		roverDto.setY(rover.getCoordinate().getY());
		roverDto.setDirection(rover.getOrientation().name());
		String currentState = "(" + rover.getCoordinate().getX() + ", " 
								  + rover.getCoordinate().getY() + ", "
							      + rover.getOrientation().getPrefix() + ")";
		roverDto.setCurrentPosition(currentState);
		return roverDto;
	}
}
