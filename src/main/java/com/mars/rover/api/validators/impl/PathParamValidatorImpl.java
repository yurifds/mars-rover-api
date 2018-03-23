package com.mars.rover.api.validators.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.mars.rover.api.validators.PathParamValidator;


@Component
public class PathParamValidatorImpl implements PathParamValidator {
	private static final String regex = "^[LMR]+$";
	private static final Pattern pattern = Pattern.compile(regex);
	
	@Override
	public boolean isValid(String param) {
		Matcher matcher = pattern.matcher(param);
		
		if (matcher.find()) return true;
			
		return false;
	}
}
