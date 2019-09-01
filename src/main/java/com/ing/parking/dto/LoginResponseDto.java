package com.ing.parking.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Verma
 *
 */
@Setter
@Getter
public class LoginResponseDto {

	private String roleName;
	private Integer employeeId;
	private String employeeName;
	private String message;
	private Integer statusCode;
	
}