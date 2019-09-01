package com.ing.parking.controller;

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

import com.ing.parking.dto.BookedSlotResponseDto;
import com.ing.parking.dto.LoginDto;
import com.ing.parking.dto.LoginResponseDto;
import com.ing.parking.dto.RegistrationRequestDto;
import com.ing.parking.dto.RegistrationResponseDto;
import com.ing.parking.service.UserService;

/**
 * @author Laxman
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginDto loginDto) {

		LOGGER.info("UserController :: userLogin");
		return new ResponseEntity<>(userService.userLogin(loginDto), HttpStatus.CREATED);
	}

	
	@PostMapping("/register")
	public ResponseEntity<RegistrationResponseDto> register(
			@RequestBody RegistrationRequestDto registrationRequestDto) {
		
		LOGGER.info("UserController :: register");
		return new ResponseEntity<>(userService.registration(registrationRequestDto),
				HttpStatus.CREATED);
	}

	
	@GetMapping("/slotBook/{employeeId}")
	public ResponseEntity<BookedSlotResponseDto> showBookedSlot(@PathVariable int employeeId) {

		LOGGER.info("UserController :: showBookedSlot");
		return new ResponseEntity<>(userService.showBookedSlot(employeeId), HttpStatus.CREATED);
	}
}
