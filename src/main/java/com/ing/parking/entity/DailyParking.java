package com.ing.parking.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Laxman
 *
 */

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class DailyParking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dailyParkingId;
	private LocalDate dailyDate;
	private Integer parkingId;
	private Integer employeeId;
}
