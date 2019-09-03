package com.ing.parking.schedul;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularTest {
			
/*	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		System.out.println("Fixed Rate Task :: Execution Time - "+ LocalDateTime.now());
	}
	
	@Scheduled(fixedDelay = 2000)
	public void scheduleTaskWithFixedDelay() {
	    System.out.println("Fixed Delay Task :: Execution Time - "+ LocalDateTime.now());
	    try {
	        TimeUnit.SECONDS.sleep(5);
	    } catch (InterruptedException ex) {
	        System.out.println("Ran into an error <> "+ ex);
	        throw new IllegalStateException(ex);
	    }
	}
	
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	public void scheduleTaskWithInitialDelay() {
	    System.out.println("Fixed Rate Task with Initial Delay :: Execution Time - "+LocalDateTime.now());
	}*/
	/**
	 * In cron job parameters are : second minute hour <day of month> <month of year> <day of week>
	 * 0-59 -  for second, minute
	 * 0-23 - for hour
	 * 1-31 - day of month
	 * 1-12 - month of year
	 * "* * * * * *" means each minutes
	 * "/ repit after, * *"/2 * * * *, repit each second after each 2 min, 10 *"/2 * * * *- 10th second after each 2nd minute"
	 * 10-30 *"/2 * * * *, each second b/t 10-30 sec at each 2nd min
	 * 10,14,20 *"/2 * * * *, each 10th 14th and 20th second in each 2nd min
	 * 
	 */
/*	@Scheduled(cron = "10-20 *"/1 * * * *")
	public void scheduledWithCron(){
		System.out.println("Cron job :: "+LocalDateTime.now());	
	}*/
}
