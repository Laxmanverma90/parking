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
public class RegistrationRequestDto {
	
	private String employeeName;
	private int experience;
	private int totalExperience;
	private String email;
	private String password;

}





