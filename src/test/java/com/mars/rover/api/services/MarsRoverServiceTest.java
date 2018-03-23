package com.mars.rover.api.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.domain.Rover;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsRoverServiceTest {
	
	@Autowired
	private MarsRoverService marsRoverService;
	
	
	@Test
	public void testForwardMove() {
		Rover rover = marsRoverService.excuteCommands("MMMM");
		assertThat(rover.getCoordinate().getX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getY(), is(equalTo(4)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('N')));
	}
	
	@Test
	public void testMoveDownward() {
		Rover rover = marsRoverService.excuteCommands("MMMMRRM");
		assertThat(rover.getCoordinate().getX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getY(), is(equalTo(3)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('S')));
	}
	
	@Test
	public void testTurnLeft() {
		Rover rover = marsRoverService.excuteCommands("MML");
		assertThat(rover.getCoordinate().getX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getY(), is(equalTo(2)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('W')));
	}
	
	@Test
	public void testTurnRight() {
		Rover rover = marsRoverService.excuteCommands("MMRMMRMM");
		assertThat(rover.getCoordinate().getX(), is(equalTo(2)));
		assertThat(rover.getCoordinate().getY(), is(equalTo(0)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('S')));
	}
}
