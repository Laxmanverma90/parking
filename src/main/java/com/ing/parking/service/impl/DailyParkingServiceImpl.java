package com.ing.parking.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.parking.entity.Assignation;
import com.ing.parking.entity.DailyParking;
import com.ing.parking.entity.Parking;
import com.ing.parking.entity.ParkingRequest;
import com.ing.parking.entity.ReleaseSlot;
import com.ing.parking.repository.AssignationRepository;
import com.ing.parking.repository.DailyParkingRepository;
import com.ing.parking.repository.ParkingRepository;
import com.ing.parking.repository.ParkingRequestRepository;
import com.ing.parking.repository.ReleaseRepository;
import com.ing.parking.service.DailyParkingService;

/**
 * @author Verma
 *
 */
@Service
public class DailyParkingServiceImpl implements DailyParkingService {

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private AssignationRepository assignationRepository;

	@Autowired
	private DailyParkingRepository dailyParkingRepository;

	@Autowired
	private ParkingRequestRepository parkingRequestRepository;

	@Autowired
	private ReleaseRepository releaseRepository;

	@Override
	public void dailyParking() {

		List<Parking> parkingList = parkingRepository.findAll();
		List<Assignation> assignatioList = assignationRepository.findAll();
		List<ParkingRequest> parkingRequestList = parkingRequestRepository.findByRequestForDateAndAllotedParkingSlotId(LocalDate.now(), 0);
		List<ReleaseSlot> releaseSlots = releaseRepository.findAllByBetweenDate();

		Map<Integer, Integer> assignMap = assignatioList.stream()
				.collect(Collectors.toMap(Assignation::getParkingId, Assignation::getEmployeeId));

		List<DailyParking> dailyParkings = new ArrayList<DailyParking>();

		parkingList.stream().forEach(parking -> {
			DailyParking dailyParking = new DailyParking();
			dailyParking.setDailyDate(LocalDate.now());
			dailyParking.setParkingId(parking.getParkingId());
			dailyParking.setEmployeeId(assignMap.get(parking.getParkingId()));
			dailyParkings.add(dailyParking);
		});

		dailyParkingRepository.saveAll(dailyParkings);
		int request = 0, requestSize =parkingRequestList.size();
		for (ReleaseSlot releaseSlot : releaseSlots) {
			if(request<requestSize){
				DailyParking dailyParking = dailyParkingRepository.findByEmployeeIdAndDailyDate(releaseSlot.getEmployeeId(),
						LocalDate.now());
				ParkingRequest parkingRequest = parkingRequestList.get(request);
				dailyParking.setEmployeeId(parkingRequest.getEmployeeId());
				parkingRequest.setAllotedParkingSlotId(dailyParking.getParkingId());
				dailyParkingRepository.save(dailyParking);
				parkingRequestRepository.save(parkingRequest);
				request++;
			}
		}

	}
}
