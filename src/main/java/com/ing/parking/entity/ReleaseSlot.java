package com.ing.parking.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Verma
 *
 */
@Entity
@Table
@Setter
@Getter
public class ReleaseSlot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int releaseId;
	private int employeeId;
	private LocalDate fromDate;
	private LocalDate toDate;

}

 



