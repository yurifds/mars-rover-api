package com.mars.rover.api.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.enums.OrientationEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoverTest {
	
	@Autowired
	private Rover rover;
	
	@Test
	public void testTurnRight() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		rover.turnFor("R");
		assertThat(rover.getOrientation(), is(OrientationEnum.EAST));
	}
	
	@Test
	public void testTurnLeft() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		rover.turnFor("L");
		assertThat(rover.getOrientation(), is(OrientationEnum.WEST));
	}
	
	@Test
	public void testMoveOn() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		assertThat(rover.getCoordinate().getY(), is(0));
		rover.moveOn();
		assertThat(rover.getCoordinate().getY(), is(1));
		assertThat(rover.getCoordinate().getX(), is(0));
	}
	
	@Test
	public void testMoveToRight() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		rover.turnFor("R");
		assertThat(rover.getOrientation(), is(OrientationEnum.EAST));
		rover.moveOn();
		assertThat(rover.getCoordinate().getX(), is(1));
		assertThat(rover.getCoordinate().getY(), is(0));
	}
	
	@Test
	public void testMoveToLeft() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		rover.turnFor("R");
		rover.moveOn();
		rover.moveOn();
		assertThat(rover.getCoordinate().getX(), is(2));
		
		rover.turnFor("L");
		rover.turnFor("L");
		
		assertThat(rover.getOrientation(), is(OrientationEnum.WEST));
		
		rover.moveOn();
		
		assertThat(rover.getCoordinate().getX(), is(1));
	}
	
	@Test
	public void testMoveToSouth() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		rover.moveOn();
		rover.moveOn();
		
		assertThat(rover.getCoordinate().getY(), is(2));
		rover.turnFor("L");
		assertThat(rover.getOrientation(), is(OrientationEnum.WEST));
		
		rover.turnFor("L");
		assertThat(rover.getOrientation(), is(OrientationEnum.SOUTH));
		
		rover.moveOn();
		
		assertThat(rover.getCoordinate().getY(), is(1));
		assertThat(rover.getOrientation(), is(OrientationEnum.SOUTH));
	}
	
	@Test
	public void testroverRotation() {
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		
		rover.turnFor("R");
		rover.turnFor("R");
		assertThat(rover.getOrientation(), is(OrientationEnum.SOUTH));
		
		rover.turnFor("R");
		rover.turnFor("R");
		assertThat(rover.getOrientation(), is(OrientationEnum.NORTH));
		
		assertThat(rover.getCoordinate().getX(), is(0));
		assertThat(rover.getCoordinate().getY(), is(0));
	}
}
