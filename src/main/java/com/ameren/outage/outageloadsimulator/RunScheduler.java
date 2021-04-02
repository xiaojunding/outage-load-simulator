package com.ameren.outage.outageloadsimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling

public class RunScheduler {

	@Autowired
	private LoadService service;
	
//	@Scheduled(fixedDelay = 15000)
	@Async
	@Scheduled(fixedRate = 10000)
	public void autoTriggerJob() {
		if(service.autoRun()) {
			System.out.println("run on schedule");
		}
	}
	
}