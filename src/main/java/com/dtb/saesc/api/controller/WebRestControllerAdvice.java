package com.dtb.saesc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorsException;
import com.dtb.saesc.api.model.response.Response;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Response handleResourceNotFoundException(ResourceNotFoundException ex) {
		return Response.error(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return Response.error(ex.getBindingResult().getAllErrors());
	}
	
	@ExceptionHandler(ValidationErrorsException.class)
	public Response handleValidationErrorsException(ValidationErrorsException ex) {
		return Response.error(ex.getErrors());
	}
}
