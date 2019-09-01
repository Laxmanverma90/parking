package com.ing.parking.schedul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ing.parking.service.DailyParkingService;

/**
 * @author Laxman
 * @date 30-08-2019
 */
@Component
public class DailyParkingScheduler {
	
	@Autowired
	private DailyParkingService dailyParkingService;
	
	/**
	 * desc : it is start the cron job for daily praking alot 
	 */
	@Scheduled(cron = "* 0/3 * * * *")
	public void cronJobSch() {
		
		dailyParkingService.dailyParking();
	}
}
