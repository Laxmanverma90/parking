package com.ing.parking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Verma
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestSlotResponseDTO {

	private String message;
	private String status;
	private int statusCode;

}
