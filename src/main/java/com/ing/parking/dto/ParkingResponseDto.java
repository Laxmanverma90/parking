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
public class ParkingResponseDto {

	private int parkingId;
	private String parkingLocation;
	private String towerName;
	private String isReserved;
}
