package com.ing.parking.exception;

public class UserAlreadyAvailable extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyAvailable(String message)
	{
		super(message);
	}

}
