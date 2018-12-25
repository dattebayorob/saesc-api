package com.dtb.saesc.api.model.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ValidationErrorsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4683899879176622500L;
	private final List<ObjectError> errors;	
	public ValidationErrorsException(List<ObjectError> errors) {
		super();
		this.errors = errors;
	}
	public List<ObjectError> getErrors() {
		return errors;
	}
	
	
}
