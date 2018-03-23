package com.mars.rover.api.validators;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.rover.api.validators.PathParamValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PathParamValidatorTest {
	
	@Autowired
	private PathParamValidator pathParamValidator;
	
	@Test
	public void testValidParameter() {
		assertThat(pathParamValidator.isValid("MMRMMRMM"), is(true));
	}
	
	@Test
	public void testInvalidParameter() {
		assertThat(pathParamValidator.isValid("MM123MMRML"), is(false));
	}
}
