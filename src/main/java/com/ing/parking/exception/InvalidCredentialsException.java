package com.ing.parking.exception;
/**
 * 
 * @author Sushil
 *
 */
public class InvalidCredentialsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException(String message)
	{
		super(message);
	}

}
