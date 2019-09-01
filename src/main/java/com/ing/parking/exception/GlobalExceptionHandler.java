package com.ing.parking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author Sushil
 *
 */
@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseError> InvalidCredentialsExceptionHandler(InvalidCredentialsException ex)
	{
		ResponseError error =  new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailIdInvalidException.class)
	public ResponseEntity<ResponseError> emailIdInvalid(EmailIdInvalidException ex)
	{
		ResponseError error =  new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserAlreadyAvailable.class)
	public ResponseEntity<ResponseError> emailIdInvalid(UserAlreadyAvailable ex)
	{
		ResponseError error =  new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RepeatedReadException.class)
	public ResponseEntity<ResponseError> repeatedReadExceptionHandler(RepeatedReadException ex)
	{
		ResponseError error =  new ResponseError(ex.getMessage(), HttpStatus.IM_USED.value());
		return new ResponseEntity<>(error, HttpStatus.IM_USED);
	}
	

}
