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
public class EmployeeRegistration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	private int roleId;
	private String employeeName;
	private int experience;
	private int totalExperience;
	private String email;
	private String password;
}
