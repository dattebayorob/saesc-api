package com.dtb.saesc.api.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dtb.saesc.api.model.exceptions.ResourceNotFoundException;
import com.dtb.saesc.api.model.exceptions.ValidationErrorException;
import com.dtb.saesc.api.model.exceptions.messages.ExceptionHandlerMessages;
import com.dtb.saesc.api.model.response.Response;
import com.dtb.saesc.api.model.response.impl.ResponseError;

@RestControllerAdvice
public class WebRestControllerAdvice {

	private static final Logger log = LoggerFactory.getLogger(WebRestControllerAdvice.class);
	private static final String INFO_MESSAGE = "Handle Exception: {}";

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Response handleResourceNotFoundException(ResourceNotFoundException ex) {
		log.info(INFO_MESSAGE, ex.getMessage());

		return ResponseError.error().add(ex.getMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.info(INFO_MESSAGE, ex.getBindingResult().getAllErrors().stream()
				.map(ObjectError::getDefaultMessage).collect(Collectors.toList()).toString());

		return ResponseError.error().list(ex.getBindingResult().getAllErrors()).build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.info(INFO_MESSAGE, ex.getMessage());

		return ResponseError.error().add(ExceptionHandlerMessages.REQUIRED_REQUEST_BODY).build();
	}

	@ExceptionHandler(ValidationErrorException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Response handleValidationErrorsException(ValidationErrorException ex) {
		log.info(INFO_MESSAGE, ex.getMessage());

		return ResponseError.error().add(ex.getMessage()).build();
	}
}
