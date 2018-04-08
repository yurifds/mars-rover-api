package com.mars.rover.api.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.rover.api.command.RoverCommand;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.exceptions.InvalidCommandException;
import com.mars.rover.api.services.MarsRoverService;

@Service
public class MarsRoverServiceImpl implements MarsRoverService {

	@Autowired
	private Rover rover;
	private static final Map<Character, RoverCommand> commands = new HashMap<Character, RoverCommand>();

	static {
		commands.put('M', Rover::moveOn);
		commands.put('R', Rover::turnRight);
		commands.put('L', Rover::turnLeft);
	}

	public Rover excuteCommands(String commands) {
		commands.chars().forEach(command -> {
			executeCommand((char) command, rover);
		});
		return rover;
	}

	private void executeCommand(char command, Rover rover) {
		if(!commands.containsKey(command)) throw new InvalidCommandException(command);		
		commands.get(command).apply(rover);
	}
}
