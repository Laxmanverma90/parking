package com.ing.parking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.DailyParking;

/**
 * @author Laxman
 *
 */
@Repository
public interface DailyParkingRepository extends JpaRepository<DailyParking, Integer> {

	public List<DailyParking> findByDailyDate(LocalDate dailyDate);
	
	public DailyParking findByEmployeeIdAndDailyDate(int employeeid, LocalDate dailyDate);

}
