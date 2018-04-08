package com.mars.rover.api.command;

import com.mars.rover.api.domain.Rover;

/**
 * RoverCommand is a functional interface that represents a Command to be executed
 * on a Rover. It is basically a {@link java.util.function.Function}, but with a
 * semantic name (and no Generics).
 */
@FunctionalInterface
public interface RoverCommand {
    void apply(Rover rover);
}
