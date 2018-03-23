package com.mars.rover.api.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.services.MarsRoverService;


@Service
public class MarsRoverServiceImpl implements MarsRoverService {
	
	@Autowired
	private Rover rover;
	
	public Rover excuteCommands(String commands) {
		Arrays.stream(commands.split("")).forEach(command -> {
			executeCommand(command);
			});
		return rover;
	}
	
	private void executeCommand(String command) {
		switch (command) {
		case "M":
			rover.moveOn();
			break;	
		case "R":
			rover.turnFor(command);
			break;
		case "L":
			rover.turnFor(command);
			break;
		}
	}
}
