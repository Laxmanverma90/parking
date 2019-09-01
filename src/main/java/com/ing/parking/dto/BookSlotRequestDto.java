package com.ing.parking.dto;

import java.time.LocalDate;

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
public class BookSlotRequestDto {

	private int employeeId;
	private int allotedParkingSlotId;
	private LocalDate requestForDate;

}
