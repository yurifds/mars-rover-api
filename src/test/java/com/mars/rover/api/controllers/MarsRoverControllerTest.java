package com.mars.rover.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mars.rover.api.enums.CardinalDirection;
import com.mars.rover.api.exceptions.BeyondLimitException;
import com.mars.rover.api.exceptions.InvalidCommandException;
import com.mars.rover.api.factories.RoverFactory;
import com.mars.rover.api.services.MarsRoverService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarsRoverControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MarsRoverService marsRoverService;

	private static final String URL_BASE = "/mars-rover/api/v1/";
	

	@Test
	public void testForwardMove() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, 4, CardinalDirection.NORTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.coordinateX").value(0))
				.andExpect(jsonPath("$.coordinateY").value(4))
				.andExpect(jsonPath("$.direction").value("NORTH"))
				.andExpect(jsonPath("$.currentPosition").value("(0, 4, N)"));
	}
	
	@Test
	public void testTurnRight() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(2, 0, CardinalDirection.SOUTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMRMMRMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.coordinateX").value(2))
				.andExpect(jsonPath("$.coordinateY").value(0))
				.andExpect(jsonPath("$.direction").value("SOUTH"))
				.andExpect(jsonPath("$.currentPosition").value("(2, 0, S)"));

	}
	
	@Test
	public void testTurnLeft() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, 2, CardinalDirection.WEST));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MML")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.coordinateX").value(0))
				.andExpect(jsonPath("$.coordinateY").value(2))
				.andExpect(jsonPath("$.direction").value("WEST"))
				.andExpect(jsonPath("$.currentPosition").value("(0, 2, W)"));
	}
	
	@Test
	public void testInvalidCommand() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
		.willThrow(new InvalidCommandException('1'));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMM12M")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Command: 1"));

	}
	
	@Test
	public void testOutOfLimit() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willThrow(new BeyondLimitException());
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMMMMMMMMMMMMMMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("This action is leaving the rover beyond of terrain limits."));
	}
}
