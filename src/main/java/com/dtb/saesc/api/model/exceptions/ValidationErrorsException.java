package com.dtb.saesc.api.model.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

public class ValidationErrorsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085636500278911220L;
	private final List<ObjectError> errors = new ArrayList<>();

	public ValidationErrorsException(ObjectError error) {
		super();
		errors.add(error);
	}
	public ValidationErrorsException(List<ObjectError> errors) {
		super();
		errors.forEach(error -> this.errors.add(error));
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

}
