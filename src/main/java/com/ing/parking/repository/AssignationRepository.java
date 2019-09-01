package com.ing.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.Assignation;

/**
 * @author laxman
 *
 */
@Repository
public interface AssignationRepository extends JpaRepository<Assignation, Integer> {

	public Assignation findByEmployeeId(int id);

}
