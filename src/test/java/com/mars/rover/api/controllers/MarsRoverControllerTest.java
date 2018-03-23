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

import com.mars.rover.api.enums.OrientationEnum;
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
	public void testInvalidCommand() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMM12M")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").isEmpty())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Command: " + "MMM12M"));

	}

	@Test
	public void testForwardMove() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, 4, OrientationEnum.NORTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.x").value(0))
				.andExpect(jsonPath("$.data.y").value(4))
				.andExpect(jsonPath("$.data.direction").value("NORTH"))
				.andExpect(jsonPath("$.data.currentPosition").value("(0, 4, N)"))
				.andExpect(jsonPath("$.errors").isEmpty());

	}
	
	@Test
	public void testTurnRight() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(2, 0, OrientationEnum.SOUTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMRMMRMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.x").value(2))
				.andExpect(jsonPath("$.data.y").value(0))
				.andExpect(jsonPath("$.data.direction").value("SOUTH"))
				.andExpect(jsonPath("$.data.currentPosition").value("(2, 0, S)"))
				.andExpect(jsonPath("$.errors").isEmpty());

	}
	
	@Test
	public void testTurnLeft() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, 2, OrientationEnum.WEST));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MML")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.x").value(0))
				.andExpect(jsonPath("$.data.y").value(2))
				.andExpect(jsonPath("$.data.direction").value("WEST"))
				.andExpect(jsonPath("$.data.currentPosition").value("(0, 2, W)"))
				.andExpect(jsonPath("$.errors").isEmpty());

	}
	
	@Test
	public void testTurnLeftOutOfLimit() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(-2, 0, OrientationEnum.SOUTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMLMMLMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").isEmpty())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Position for the following commands: " + "MMLMMLMM"));

	}
	
	@Test
	public void testTurnRightOutOfLimit() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(6, 0, OrientationEnum.EAST));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "RMMMMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").isEmpty())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Position for the following commands: " + "RMMMMMM"));

	}
	
	@Test
	public void testForwardMoveOutOfLimit() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, 6, OrientationEnum.NORTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "MMMMMMMMMMMMMMMMMMMMMMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").isEmpty())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Position for the following commands: " + "MMMMMMMMMMMMMMMMMMMMMMMM"));

	}
	
	@Test
	public void testMoveDownwardOutOfLimit() throws Exception {
		BDDMockito.given(this.marsRoverService.excuteCommands(Mockito.any(String.class)))
				.willReturn(RoverFactory.buildRover(0, -6, OrientationEnum.NORTH));
		
		mvc.perform(MockMvcRequestBuilders.post(URL_BASE + "RRMMMMMM")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").isEmpty())
				.andExpect(jsonPath("$.errors").isNotEmpty())
				.andExpect(jsonPath("$.errors").value("Invalid Position for the following commands: " + "RRMMMMMM"));

	}
}
