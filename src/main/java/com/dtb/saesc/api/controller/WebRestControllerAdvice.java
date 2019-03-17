package com.dtb.saesc.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseError;

@RestControllerAdvice
public class WebRestControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Response handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseError.error().add(ex.getMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ResponseError.error().list(ex.getBindingResult().getAllErrors()).build();
	}

	@ExceptionHandler(ValidationErrorException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleValidationErrorsException(ValidationErrorException ex) {
		return ResponseError.error().add(ex.getMessage()).build();
	}
}
