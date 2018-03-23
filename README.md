# mars-rover-api

[![Build Status](https://travis-ci.org/yurifds/mars-rover-api.svg?branch=master)](https://travis-ci.org/yurifds/mars-rover-api) [![Maintainability](https://api.codeclimate.com/v1/badges/757acf288684fe9927ae/maintainability)](https://codeclimate.com/github/yurifds/mars-rover-api/maintainability)


### The Challenge

A squad of robotic rovers are to be landed by NASA on a plateau on Mars.

This plateau, which is curiously rectangular, must be navigated by the rovers so that their on board cameras can get a complete view of the surrounding terrain to send back to Earth.

A rover's position is represented by a combination of an x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or right respectively, without moving from its current spot.

'M' means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).


### Challenge Requirements 

- The Terrain must start with 5x5 Positions;
- The Rover starts at coordinate (0,0,N);
- Must be possible sends commands to the Rover and return the current position him.
- The Rover may not exceed the terrain limit of 5x5 positions.
- The API can't maintain state between requests.


### Language and Libraries used:

- Java 8
- Spring Framework
- Swagger

## Usage

- To run the server: in the project directory `mvn spring-boot:run` starts the project.
- Use `mvn test` in the project directory to run all the tests.
- With the server started, you can access the Swagger documentation and interact with the API
using the [link](http://localhost:8080/).

### Endpoints

|  Method  |     Endpoint   | Description |
|----------|----------------|-------------|
| POST | /mars-rover/api/v1/:commands | Sends commands for the Rover and returns the current location. |
