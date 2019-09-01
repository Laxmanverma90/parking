package com.ing.parking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Verma
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class RegistrationResponseDto {

	private String message;
	private int statusCode;
	private String status;
	
}

