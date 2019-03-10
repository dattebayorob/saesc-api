package com.dtb.saesc.api.model.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3104584320303595282L;
	public ResourceNotFoundException(String s) {
		super(s);
	}
}
