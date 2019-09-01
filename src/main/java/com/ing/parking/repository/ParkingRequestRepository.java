package com.ing.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.ParkingRequest;

/**
 * @author Verma
 *
 */
@Repository
public interface ParkingRequestRepository extends JpaRepository<ParkingRequest, Integer> {

	List<ParkingRequest> findByEmployeeId(int employeeId);

	List<ParkingRequest> findByRequestForDate(LocalDate requestDate);
}
