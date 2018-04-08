package com.mars.rover.api.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.exceptions.BeyondLimitException;
import com.mars.rover.api.exceptions.InvalidCommandException;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MarsRoverServiceTest {
	
	@Autowired
	private MarsRoverService marsRoverService;
	
	@Rule
    public ExpectedException invalidCommandException = ExpectedException.none();
	
	@Rule
    public ExpectedException beyondLimitException = ExpectedException.none();
	
	@Test
	public void testForwardMove() {
		Rover rover = marsRoverService.excuteCommands("MMMM");
		assertThat(rover.getCoordinate().getCoordinateX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getCoordinateY(), is(equalTo(4)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('N')));
	}
	
	@Test
	public void testMoveDownward() {
		Rover rover = marsRoverService.excuteCommands("MMMMRRM");
		assertThat(rover.getCoordinate().getCoordinateX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getCoordinateY(), is(equalTo(3)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('S')));
	}
	
	@Test
	public void testTurnLeft() {
		Rover rover = marsRoverService.excuteCommands("MML");
		assertThat(rover.getCoordinate().getCoordinateX(), is(equalTo(0)));
		assertThat(rover.getCoordinate().getCoordinateY(), is(equalTo(2)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('W')));
	}
	
	@Test
	public void testTurnRight() {
		Rover rover = marsRoverService.excuteCommands("MMRMMRMM");
		assertThat(rover.getCoordinate().getCoordinateX(), is(equalTo(2)));
		assertThat(rover.getCoordinate().getCoordinateY(), is(equalTo(0)));
		assertThat(rover.getOrientation().getPrefix(), is(equalTo('S')));
	}
	
	@Test
	public void testInvalidCommand() {
		invalidCommandException.expect(InvalidCommandException.class);
		invalidCommandException.expectMessage("Invalid Command: 1");
		marsRoverService.excuteCommands("MMM12M");
	}
	
	@Test
	public void testTurnLeftOutOfLimit() {
		beyondLimitException.expect(BeyondLimitException.class);
		beyondLimitException.expectMessage("This action is leaving the rover beyond of terrain limits.");
		marsRoverService.excuteCommands("MMLMMLMM");		
	}
	
	@Test
	public void testTurnRightOutOfLimit() {
		beyondLimitException.expect(BeyondLimitException.class);
		beyondLimitException.expectMessage("This action is leaving the rover beyond of terrain limits.");
		marsRoverService.excuteCommands("RMMMMMM");		
	}
	
	@Test
	public void testForwardMoveOutOfLimit() {
		beyondLimitException.expect(BeyondLimitException.class);
		beyondLimitException.expectMessage("This action is leaving the rover beyond of terrain limits.");
		marsRoverService.excuteCommands("MMMMMMMMMMMMMMMMMMMMMMMM");	
	}
	
	@Test
	public void testMoveDownwardOutOfLimit() {
		beyondLimitException.expect(BeyondLimitException.class);
		beyondLimitException.expectMessage("This action is leaving the rover beyond of terrain limits.");
		marsRoverService.excuteCommands("RRMMMMMM");
	}
}
