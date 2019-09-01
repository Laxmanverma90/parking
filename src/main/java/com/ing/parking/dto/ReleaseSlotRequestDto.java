package com.ing.parking.dto;

import java.time.LocalDate;

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
public class ReleaseSlotRequestDto {

	private int employeeId;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int parkingslotId;
	
	
}

