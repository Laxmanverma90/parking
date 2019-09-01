package com.ing.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.parking.entity.Parking;

/**
 * @author Laxman
 *
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

	public List<Parking> findByIsReserved(String status);

	public Parking findByParkingId(int parkingId);

}
