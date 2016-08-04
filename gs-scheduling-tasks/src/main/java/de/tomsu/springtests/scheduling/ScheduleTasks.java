package de.tomsu.springtests.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTasks {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:sss");
	
	@Scheduled(fixedRate=200)
	public void reportCurrentTime() {
		System.out.println("The time is now " + dateFormat.format(new Date()));
	}
}
