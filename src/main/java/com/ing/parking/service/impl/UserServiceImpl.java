package com.ing.parking.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.parking.dto.BookedSlotResponseDto;
import com.ing.parking.dto.LoginDto;
import com.ing.parking.dto.LoginResponseDto;
import com.ing.parking.dto.RegistrationRequestDto;
import com.ing.parking.dto.RegistrationResponseDto;
import com.ing.parking.entity.Assignation;
import com.ing.parking.entity.EmployeeRegistration;
import com.ing.parking.entity.Parking;
import com.ing.parking.entity.Role;
import com.ing.parking.exception.EmailIdInvalidException;
import com.ing.parking.exception.InvalidCredentialsException;
import com.ing.parking.exception.UserAlreadyAvailable;
import com.ing.parking.repository.AssignationRepository;
import com.ing.parking.repository.EmployeeRegistrationRepository;
import com.ing.parking.repository.ParkingRepository;
import com.ing.parking.repository.RoleRepository;
import com.ing.parking.service.UserService;

/**
 * @author Laxman Service Class is to user Registration and user login Allready
 *         Registerd user can see their details
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private EmployeeRegistrationRepository registrationRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private AssignationRepository assignationRepository;

	@Override
	public LoginResponseDto userLogin(LoginDto loginDto) {

		LOGGER.info("UserServiceImpl :: userLogin  -- STATRS");

		LoginResponseDto loginResponseDto = new LoginResponseDto();

		/* get user object based on specific user */
		EmployeeRegistration employee = registrationRepository.findByEmail(loginDto.getEmailId());
		if (employee != null) {
			if (loginDto.getPassword().equalsIgnoreCase(employee.getPassword())) {

				Role role = roleRepository.findByRoleId(employee.getRoleId());

				loginResponseDto.setEmployeeId(employee.getEmployeeId());
				loginResponseDto.setRoleName(role.getRoleName());
				loginResponseDto.setEmployeeName(employee.getEmployeeName());
				loginResponseDto.setMessage("User has  been logged in successfully");
				loginResponseDto.setStatusCode(201);
			} else {
				throw new InvalidCredentialsException("User/Password is wrong !!!");
			}
		} else {
			throw new InvalidCredentialsException("Invalid User");
		}
		LOGGER.info("UserServiceImpl :: userLogin  -- ENDS");

		return loginResponseDto;
	}

	/**
	 * This method is used to register the employee for parking. This is a the
	 * simplest form of a class method, just to show that the business rule is
	 * performed i.e VIP employee get a parking slot
	 * 
	 * @param RegistrationRequestDto
	 *            registrationRequestDto This is the paramter to register for
	 *            parking
	 * @return RegistrationResponseDto This returns status of the parking slot
	 */
	@Override
	public RegistrationResponseDto registration(RegistrationRequestDto registrationRequestDto) {

		LOGGER.info("UserServiceImpl :: registration  -- STARTS");

		EmployeeRegistration employeeRegistration = new EmployeeRegistration();
		Assignation assignation = new Assignation();

		RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
		if (isValidEmailAddress(registrationRequestDto.getEmail())) {
			EmployeeRegistration empRegister = registrationRepository.findByEmail(registrationRequestDto.getEmail());
			if (empRegister == null) {

				employeeRegistration.setEmail(registrationRequestDto.getEmail());
				employeeRegistration.setEmployeeName(registrationRequestDto.getEmployeeName());
				employeeRegistration.setExperience(registrationRequestDto.getExperience());
				employeeRegistration.setTotalExperience(registrationRequestDto.getTotalExperience());
				employeeRegistration.setPassword(registrationRequestDto.getPassword());

				if (registrationRequestDto.getExperience() >= 5 && registrationRequestDto.getTotalExperience() >= 15) {
					Role vipRole = roleRepository.findByRoleName("VIP");
					employeeRegistration.setRoleId(vipRole.getRoleId());
				} else {
					Role regularRole = roleRepository.findByRoleName("REGULAR");
					employeeRegistration.setRoleId(regularRole.getRoleId());
					registrationResponseDto.setMessage("Registration successful");
				}

				EmployeeRegistration saveRegister = registrationRepository.save(employeeRegistration);
				if (saveRegister.getRoleId() == 1) {
					assignation.setEmployeeId(saveRegister.getEmployeeId());
					List<Parking> parkingAllot = parkingRepository.findByIsReserved("false");
					if (!parkingAllot.isEmpty()) {
						Parking parking = parkingAllot.get(0);
						parking.setIsReserved("true");
						parkingRepository.save(parking);

						assignation.setParkingId(parking.getParkingId());
						assignationRepository.save(assignation);

						registrationResponseDto
								.setMessage("Parking slot no " + parking.getParkingId() + " booked successfully");
					}
					/*
					 * for (Parking parking : parkingAllot) {
					 * parking.setIsReserved("true");
					 * parkingRepository.save(parking);
					 * assignation.setParkingId(parking.getParkingId());
					 * registrationResponseDto.
					 * setMessage("slot booked successfully");
					 * assignationRepository.save(assignation); }
					 */
				}

				registrationResponseDto.setStatusCode(201);
				registrationResponseDto.setStatus("Success");
			} else {
				throw new UserAlreadyAvailable("User Already exist");
			}
		} else {
			throw new EmailIdInvalidException("email Id is invalid");
		}
		LOGGER.info("UserServiceImpl :: registration  -- ENDS");
		return registrationResponseDto;
	}

	/**
	 * This method is used to validate email pattern
	 * 
	 * @param String
	 *            email .This parameter is the input
	 * @return boolean This returns true if valid email and return false if
	 *         email is invalid
	 */
	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public BookedSlotResponseDto showBookedSlot(int id) {

		LOGGER.info("UserServiceImpl :: showBookedSlot  -- STARTS");

		Assignation assignation = assignationRepository.findByEmployeeId(id);
		BookedSlotResponseDto bookedSlotResponseDto = new BookedSlotResponseDto();

		if(assignation!=null){
			Parking parking = parkingRepository.findByParkingId(assignation.getParkingId());
			bookedSlotResponseDto.setParkingId(parking.getParkingId());
			bookedSlotResponseDto.setParkingLocation(parking.getParkingLocation());
		}

		LOGGER.info("UserServiceImpl :: showBookedSlot  -- ENDS");
		return bookedSlotResponseDto;
	}
}
