package com.ing.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.EmployeeRegistration;

/**
 * @author Laxman
 *
 */
@Repository
public interface EmployeeRegistrationRepository extends JpaRepository<EmployeeRegistration, Integer> {
	
	public EmployeeRegistration findByEmail(String email);
	
}
