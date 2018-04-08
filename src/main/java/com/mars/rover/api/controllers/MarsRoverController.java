package com.mars.rover.api.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.rover.api.converter.impl.ConvertToRoverDto;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.dtos.RoverDto;
import com.mars.rover.api.services.MarsRoverService;

@RestController
@RequestMapping("mars-rover/api/v1")
@Api(tags = {"MarsRover"})
public class MarsRoverController {

	@Autowired
	MarsRoverService roverService;
	
	@Autowired
	ConvertToRoverDto convertToRoverDto;
	
	/**
	 * Sends commands to Rover on Mars.
	 * 
	 * @param command
	 * @return ResponseEntity<Response<RoverDto>>
	 */
	@PostMapping(value = "/{command}")
	@ApiOperation(value = "Sends commands to Rover on Mars", notes = "Returns a Rover with the current location.")
	public ResponseEntity<RoverDto> excuteCommands(@PathVariable("command") String command){
		Rover rover = roverService.excuteCommands(command);		
		return ResponseEntity.ok(convertToRoverDto.convert(rover));
	}
}
