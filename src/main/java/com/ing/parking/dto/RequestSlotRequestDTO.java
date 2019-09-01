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
public class RequestSlotRequestDTO {
	private int employeeId;
	private LocalDate requestForDate;

}
