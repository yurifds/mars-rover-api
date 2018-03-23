package com.mars.rover.api.validators;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.factories.CoordinateFactory;
import com.mars.rover.api.validators.TerrainLimitValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TerrainLimitValidatorTest {
	
	@Autowired
	private TerrainLimitValidator terrainLimitValidator;
	
	
	
	@Test
	public void testValidCommand() {
		assertThat(terrainLimitValidator.isValid(CoordinateFactory.buildCoordinate(0, 4)), is(true));
	}
	
	@Test
	public void testInvalidCommand() {
		assertThat(terrainLimitValidator.isValid(CoordinateFactory.buildCoordinate(7, 0)), is(false));
	}
}
