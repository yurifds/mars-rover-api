package com.mars.rover.api.command;

import com.mars.rover.api.domain.Position;

/**
 * CoordinateCommand is a functional interface that represents the "go forward" command. 
 * It is basically a {@link java.util.function.Function}, but with a
 * semantic name (and no Generics).
 */
@FunctionalInterface
public interface CoordinateCommand {
	void apply(Position position);
}
