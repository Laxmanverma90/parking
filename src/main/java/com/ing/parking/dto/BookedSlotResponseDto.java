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
public class BookedSlotResponseDto {
	
	private String parkingLocation;
	private int parkingId;
}
