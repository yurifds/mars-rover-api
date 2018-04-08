package com.mars.rover.api.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mars.rover.api.error.response.ErrorResponse;
import com.mars.rover.api.exceptions.BeyondLimitException;
import com.mars.rover.api.exceptions.InvalidCommandException;

@ControllerAdvice
public class ResponseExceptionHandler {

	@ResponseBody
	@ExceptionHandler(Throwable.class)
	ResponseEntity<ErrorResponse> customHandler(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.addError(ex.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(value = {BeyondLimitException.class, InvalidCommandException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse roverExceptionHandler(RuntimeException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.addError(ex.getLocalizedMessage());
		return errorResponse;
	}
}
