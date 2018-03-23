package com.mars.rover.api.validators.impl;

import org.springframework.stereotype.Component;

import com.mars.rover.api.domain.Coordinate;
import com.mars.rover.api.validators.TerrainLimitValidator;

@Component
public class TerrainLimitValidatorImpl implements TerrainLimitValidator {
	
	@Override
	public boolean isValid(Coordinate coordinate) {
		if (coordinate.getY() > terrainMaxcoordinate
				|| coordinate.getY() < terrainMincoordinate
				|| coordinate.getX() > terrainMaxcoordinate
				|| coordinate.getX() < terrainMincoordinate) {
			return false;
			
		}
		return true;
	}
}
