package com.ing.parking.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ing.parking.dto.BookSlotRequestDto;
import com.ing.parking.dto.ParkingResponseDto;
import com.ing.parking.dto.ReleaseSlotRequestDto;
import com.ing.parking.dto.ReleaseSlotResponeDto;
import com.ing.parking.dto.RequestSlotRequestDTO;
import com.ing.parking.dto.RequestSlotResponseDTO;
import com.ing.parking.service.ParkingService;

/**
 * 
 * @author Laxman
 *
 */
@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class ParkingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ParkingService parkingService;

	/**
	 * @return ResponseEntity<List<ParkingResponseDto>>
	 */
	@GetMapping("/showSlots")
	public ResponseEntity<List<ParkingResponseDto>> getAllSlot() {

		LOGGER.info("ParkingController :: getAllSlot --");
		return new ResponseEntity<List<ParkingResponseDto>>(parkingService.getAllSlot(), HttpStatus.OK);
	}

	/**
	 * @param dailyDate
	 * @return
	 */
	@GetMapping("/showDailySlot/{dailyDate}")
	public ResponseEntity<List<ParkingResponseDto>> getDailyAllSlot(@PathVariable String dailyDate) {

		LOGGER.info("ParkingController :: getDailyAllSlot --");
		LocalDate localDate = LocalDate.parse(dailyDate);
		return new ResponseEntity<List<ParkingResponseDto>>(parkingService.getDailySlot(localDate), HttpStatus.OK);
	}

	/**
	 * @param releaseSlotRequestDto
	 * @return
	 */
	@PostMapping("/releaseSlot")
	public ResponseEntity<ReleaseSlotResponeDto> releaseSlot(@RequestBody ReleaseSlotRequestDto releaseSlotRequestDto) {

		LOGGER.info("ParkingController :: releaseSlot --");
		return new ResponseEntity<>(parkingService.releaseSlot(releaseSlotRequestDto), HttpStatus.CREATED);
	}

	/**
	 * @param requestSlotRequestDTO
	 * @return
	 */
	@PostMapping("/requestSlot")
	public ResponseEntity<RequestSlotResponseDTO> requestSlot(
			@RequestBody RequestSlotRequestDTO requestSlotRequestDTO) {

		LOGGER.info("ParkingController :: requestSlot --");
		return new ResponseEntity<>(parkingService.requestSlot(requestSlotRequestDTO), HttpStatus.OK);
	}

	/**
	 * @param bookSlotRequestDto
	 * @return
	 */
	@PostMapping("/bookSlot")
	public ResponseEntity<RequestSlotResponseDTO> bookSlot(@RequestBody BookSlotRequestDto bookSlotRequestDto) {

		LOGGER.info("ParkingController :: bookSlot --");
		return new ResponseEntity<>(parkingService.bookSlot(bookSlotRequestDto), HttpStatus.OK);
	}
}
