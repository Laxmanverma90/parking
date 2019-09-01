package com.ing.parking.service;

import java.time.LocalDate;
import java.util.List;

import com.ing.parking.dto.BookSlotRequestDto;
import com.ing.parking.dto.ParkingResponseDto;
import com.ing.parking.dto.ReleaseSlotRequestDto;
import com.ing.parking.dto.ReleaseSlotResponeDto;
import com.ing.parking.dto.RequestSlotRequestDTO;
import com.ing.parking.dto.RequestSlotResponseDTO;

/**
 * @author laxman
 *
 */
public interface ParkingService {

	public List<ParkingResponseDto> getAllSlot();

	public List<ParkingResponseDto> getDailySlot(LocalDate dailyDate);

	public ReleaseSlotResponeDto releaseSlot(ReleaseSlotRequestDto releaseSlotRequestDto);

	RequestSlotResponseDTO requestSlot(RequestSlotRequestDTO requestSlotRequestDTO);

	RequestSlotResponseDTO bookSlot(BookSlotRequestDto bookSlotRequestDto);
}
