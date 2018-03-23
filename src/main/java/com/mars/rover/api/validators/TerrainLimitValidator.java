package com.mars.rover.api.validators;

import com.mars.rover.api.domain.Coordinate;

public interface TerrainLimitValidator {
	Integer terrainMaxcoordinate = 5;
	Integer terrainMincoordinate = 0;
	
	/**
	 * Validates if a given rover exceeded the terrain limit
	 * @param Coordinate
	 * @return boolean
	 */
	boolean isValid(Coordinate coordinate);
}
