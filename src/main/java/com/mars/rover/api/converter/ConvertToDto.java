package com.mars.rover.api.converter;

public interface ConvertToDto<T, D> {
	
	T convert(D d);
}
