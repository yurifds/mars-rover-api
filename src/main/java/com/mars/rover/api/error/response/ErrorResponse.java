package com.mars.rover.api.error.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorResponse {

	private List<String> errors;
	
	public ErrorResponse() {
		errors = new ArrayList<>();
	}
	
	public void addError(String erro) {
		this.errors.add(erro);
	}
	
	public List<String> getErrors() {
		return Collections.unmodifiableList(errors);
	}
}
