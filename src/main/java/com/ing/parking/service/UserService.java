package com.ing.parking.service;

import com.ing.parking.dto.BookedSlotResponseDto;
import com.ing.parking.dto.LoginDto;
import com.ing.parking.dto.LoginResponseDto;
import com.ing.parking.dto.RegistrationRequestDto;
import com.ing.parking.dto.RegistrationResponseDto;

public interface UserService {

	LoginResponseDto userLogin(LoginDto loginDto);

	public RegistrationResponseDto registration(RegistrationRequestDto registrationRequestDto);

	public BookedSlotResponseDto showBookedSlot(int id);

}
