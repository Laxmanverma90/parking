package com.ing.parking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Verma
 *
 */
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Assignation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assignId;
	private int employeeId;
	private int parkingId;
}
