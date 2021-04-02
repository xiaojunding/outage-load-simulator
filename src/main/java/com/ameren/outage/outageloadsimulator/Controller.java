package com.ameren.outage.outageloadsimulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private LoadService service;

	@RequestMapping(value = "/setup", method = RequestMethod.GET, produces = "text/plain")
	public String setupLoadTest() {
		System.out.println("Start preparing load test data");
		service.buildData();
		return "Done";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "text/plain")
	public String getPayload(@RequestParam(required = false, name = "run") Integer count) {
		if (count != null && count > 0) {
			String payload = service.getPayload(count);
			return payload;
		} else {
			return "Done";
		}
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET, produces = "text/plain")
	public String startLoadTest(@RequestParam(required = false, name = "run") Integer count, @RequestParam(required = false, name = "env") String env ) {
		if (count != null && count > 0) {
			service.setStartFrom(count);
			service.setEnv(env);
			service.setAutoRun(true);
		}
		
		return "Done";
	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET, produces = "text/plain")
	public String stopLoadTest() {
		service.setAutoRun(false);
		return "Done";
	}
	
	@RequestMapping(value = "/resume", method = RequestMethod.GET, produces = "text/plain")
	public String resumeLoadTest() {
		service.setAutoRun(true);
		return "Done";
	}
	
	@RequestMapping(value = "/getCache", method = RequestMethod.GET, produces = "text/plain")
	public String getPayloadCache(@RequestParam(required = false, name = "run") Integer count) {
		if (count != null && count > 0) {
			String payload = service.getPayload(count);
			return payload;
		} else {
			return "Not Found";
		}
	}
}
