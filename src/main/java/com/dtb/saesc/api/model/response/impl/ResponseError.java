package com.dtb.saesc.api.model.response.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.ObjectError;

import com.dtb.saesc.api.model.response.Response;

public class ResponseError implements Response{
	private List<String> errors;
	
	public ResponseError() {
		
	}
	
	public ResponseError(List<String> errors) {
		this.errors = errors;
	}
	
	public static ResponseError error() {
		return new ResponseError();
	}
	
	public ResponseError add(String error) {
		if(this.errors == null)
			this.errors = new ArrayList<>();
		this.errors.add(error);
		return this;
	}
	
	public ResponseError list(List<ObjectError> errors) {
		this.errors = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
		return this;
	}
	
	public ResponseError build() {
		return new ResponseError(errors);
	}
	
	public static ResponseError exception(RuntimeException ex) {
		throw ex;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
