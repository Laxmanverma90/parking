package com.ing.parking.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.parking.dto.BookSlotRequestDto;
import com.ing.parking.dto.ParkingResponseDto;
import com.ing.parking.dto.ReleaseSlotRequestDto;
import com.ing.parking.dto.ReleaseSlotResponeDto;
import com.ing.parking.dto.RequestSlotRequestDTO;
import com.ing.parking.dto.RequestSlotResponseDTO;
import com.ing.parking.entity.DailyParking;
import com.ing.parking.entity.Parking;
import com.ing.parking.entity.ParkingRequest;
import com.ing.parking.entity.ReleaseSlot;
import com.ing.parking.exception.RepeatedReadException;
import com.ing.parking.repository.DailyParkingRepository;
import com.ing.parking.repository.ParkingRepository;
import com.ing.parking.repository.ParkingRequestRepository;
import com.ing.parking.repository.ReleaseRepository;
import com.ing.parking.service.ParkingService;

/**
 * @author Laxman
 *
 */
@Service
public class ParkingServiceImpl implements ParkingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingServiceImpl.class);

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private DailyParkingRepository dailyParkingRepository;

	@Autowired
	private ParkingRequestRepository parkingRequestRepository;

	@Autowired
	private ReleaseRepository releaseRepository;

	@Override
	public List<ParkingResponseDto> getAllSlot() {

		LOGGER.info("ParkingServiceImpl :: getAllSlot -- START");

		List<ParkingResponseDto> response = new ArrayList<>();
		List<Parking> listParking = parkingRepository.findAll();

		listParking.stream().forEach(parking -> {
			ParkingResponseDto responseDto = new ParkingResponseDto();
			responseDto.setParkingId(parking.getParkingId());
			responseDto.setTowerName(parking.getTowerName());
			responseDto.setIsReserved(parking.getIsReserved());
			responseDto.setParkingLocation(parking.getParkingLocation());

			response.add(responseDto);
		});
		/*
		 * for (Parking parking : listParking) { ParkingResponseDto responseDto
		 * = new ParkingResponseDto();
		 * responseDto.setParkingId(parking.getParkingId());
		 * responseDto.setTowerName(parking.getTowerName());
		 * responseDto.setIsReserved(parking.getIsReserved());
		 * responseDto.setParkingLocation(parking.getParkingLocation());
		 * 
		 * response.add(responseDto); }
		 */
		LOGGER.info("ParkingServiceImpl :: getAllSlot -- END");
		return response;
	}

	@Override
	public List<ParkingResponseDto> getDailySlot(LocalDate dailyDate) {

		LOGGER.info("ParkingServiceImpl :: getDailySlot -- START");

		List<ParkingResponseDto> response = new ArrayList<>();
		List<DailyParking> listPark = dailyParkingRepository.findByDailyDate(dailyDate);

		for (DailyParking dailyParking : listPark) {
			Parking parking = parkingRepository.findByParkingId(dailyParking.getParkingId());
			ParkingResponseDto responseDto = new ParkingResponseDto();
			LOGGER.info("data dailyParking.getEmployeeId() ={}", dailyParking.getEmployeeId());
			if (dailyParking.getEmployeeId() == null) {
				responseDto.setIsReserved("true");
			} else {
				responseDto.setIsReserved("false");
			}
			responseDto.setParkingId(parking.getParkingId());
			responseDto.setTowerName(parking.getTowerName());
			responseDto.setParkingLocation(parking.getParkingLocation());

			response.add(responseDto);
		}
		LOGGER.info("ParkingServiceImpl :: getDailySlot -- END");
		return response;
	}

	@Override
	public ReleaseSlotResponeDto releaseSlot(ReleaseSlotRequestDto releaseSlotRequestDto) {

		LOGGER.info("ParkingServiceImpl :: releaseSlot -- START");

		ReleaseSlotResponeDto releaseSlotResponeDto = new ReleaseSlotResponeDto();

		ReleaseSlot release = new ReleaseSlot();
		release.setEmployeeId(releaseSlotRequestDto.getEmployeeId());
		release.setFromDate(releaseSlotRequestDto.getFromDate());
		release.setToDate(releaseSlotRequestDto.getToDate());

		releaseRepository.save(release);
		releaseSlotResponeDto.setMessage("slot released");
		releaseSlotResponeDto.setStatusCode(201);
		releaseSlotResponeDto.setStatus("Success");

		LOGGER.info("ParkingServiceImpl :: releaseSlot -- END");
		return releaseSlotResponeDto;
	}

	@Override
	public RequestSlotResponseDTO requestSlot(RequestSlotRequestDTO requestSlotRequestDTO) {

		LOGGER.info("ParkingServiceImpl :: requestSlot -- START");

		RequestSlotResponseDTO requestSlotResponseDTO = new RequestSlotResponseDTO();
		List<ParkingRequest> parkingList = parkingRequestRepository
				.findByEmployeeId(requestSlotRequestDTO.getEmployeeId());

		if (parkingList.isEmpty()) {
			ParkingRequest parkingRequest = new ParkingRequest();

			BeanUtils.copyProperties(requestSlotRequestDTO, parkingRequest);
			parkingRequestRepository.save(parkingRequest);

			requestSlotResponseDTO.setMessage("Request Sent Successfully");
			requestSlotResponseDTO.setStatusCode(201);
			requestSlotResponseDTO.setStatus("Success");
		} else {
			requestSlotResponseDTO.setMessage("Already Requested for a Slot");
			requestSlotResponseDTO.setStatusCode(201);
			requestSlotResponseDTO.setStatus("Failed");
		}
		LOGGER.info("ParkingServiceImpl :: requestSlot -- END");
		return requestSlotResponseDTO;

	}

	@Override
	public RequestSlotResponseDTO bookSlot(BookSlotRequestDto bookSlotRequestDto) {

		LOGGER.info("ParkingServiceImpl :: bookSlot -- START");

		RequestSlotResponseDTO bookSlot = new RequestSlotResponseDTO();

		List<ParkingRequest> parkingList = parkingRequestRepository
				.findByEmployeeId(bookSlotRequestDto.getEmployeeId());

		if (parkingList.isEmpty()) {

			ParkingRequest parkingRequest = new ParkingRequest();

			BeanUtils.copyProperties(bookSlotRequestDto, parkingRequest);
			parkingRequestRepository.save(parkingRequest);

			bookSlot.setMessage("Booked the slot Successfully");
			bookSlot.setStatusCode(201);
			bookSlot.setStatus("Success");
		} else {
			throw new RepeatedReadException("Slot Already Booked");
		}
		LOGGER.info("ParkingServiceImpl :: bookSlot -- END");
		return bookSlot;
	}

}
