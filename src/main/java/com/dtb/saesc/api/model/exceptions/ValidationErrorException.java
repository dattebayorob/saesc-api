package com.dtb.saesc.api.model.exceptions;

public class ValidationErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085636500278911220L;

	public ValidationErrorException(String msg) {
		super(msg);
	}

}
