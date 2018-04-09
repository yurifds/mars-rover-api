package com.mars.rover.api.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.enums.CardinalDirection;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoverTest {
	
	@Autowired
	private Rover rover;
	
	@Test
	public void testTurnRight() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		rover.turnRight();
		assertThat(rover.getOrientation(), is(CardinalDirection.EAST));
	}
	
	@Test
	public void testTurnLeft() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		rover.turnLeft();
		assertThat(rover.getOrientation(), is(CardinalDirection.WEST));
	}
	
	@Test
	public void testMoveOn() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		assertThat(rover.getCoordinate().getCoordinateY(), is(0));
		rover.goForward();
		assertThat(rover.getCoordinate().getCoordinateY(), is(1));
		assertThat(rover.getCoordinate().getCoordinateX(), is(0));
	}
	
	@Test
	public void testMoveToRight() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		rover.turnRight();
		assertThat(rover.getOrientation(), is(CardinalDirection.EAST));
		rover.goForward();
		assertThat(rover.getCoordinate().getCoordinateX(), is(1));
		assertThat(rover.getCoordinate().getCoordinateY(), is(0));
	}
	
	@Test
	public void testMoveToLeft() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		rover.turnRight();
		rover.goForward();
		rover.goForward();
		assertThat(rover.getCoordinate().getCoordinateX(), is(2));
		
		rover.turnLeft();
		rover.turnLeft();
		
		assertThat(rover.getOrientation(), is(CardinalDirection.WEST));
		
		rover.goForward();
		
		assertThat(rover.getCoordinate().getCoordinateX(), is(1));
	}
	
	@Test
	public void testMoveToSouth() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		rover.goForward();
		rover.goForward();
		
		assertThat(rover.getCoordinate().getCoordinateY(), is(2));
		rover.turnLeft();
		assertThat(rover.getOrientation(), is(CardinalDirection.WEST));
		
		rover.turnLeft();
		assertThat(rover.getOrientation(), is(CardinalDirection.SOUTH));
		
		rover.goForward();
		
		assertThat(rover.getCoordinate().getCoordinateY(), is(1));
		assertThat(rover.getOrientation(), is(CardinalDirection.SOUTH));
	}
	
	@Test
	public void testroverRotation() {
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		
		rover.turnRight();
		rover.turnRight();
		assertThat(rover.getOrientation(), is(CardinalDirection.SOUTH));
		
		rover.turnRight();
		rover.turnRight();
		assertThat(rover.getOrientation(), is(CardinalDirection.NORTH));
		
		assertThat(rover.getCoordinate().getCoordinateX(), is(0));
		assertThat(rover.getCoordinate().getCoordinateY(), is(0));
	}
}
