package com.mars.rover.api.converter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.converter.impl.ConvertToRoverDto;
import com.mars.rover.api.domain.Rover;
import com.mars.rover.api.dtos.RoverDto;
import com.mars.rover.api.enums.CardinalDirection;
import com.mars.rover.api.factories.RoverFactory;

@RunWith(SpringRunner.class) 
@SpringBootTest 
public class ConvertToRoverDtoTest {
	
	@Autowired
	ConvertToRoverDto convertToRoverDto;
	
	@Test
	public void testConvertRoverDto() {
		Rover rover = RoverFactory.buildRover(0, 4, CardinalDirection.NORTH);
		RoverDto roverDto = convertToRoverDto.convert(rover);
		
		assertThat(roverDto.getCoordinateX(), is(equalTo(0)));
		assertThat(roverDto.getCoordinateY(), is(equalTo(4)));
		assertThat(roverDto.getDirection(), is(equalTo("NORTH")));
		assertThat(roverDto.getCurrentPosition(), is(equalTo("(0, 4, N)")));
	}
}
