package com.ameren.outage.outageloadsimulator;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ameren.outage.outageloadsimulator.model.SnapshotManager;
import com.ameren.outage.outageloadsimulator.model.SnapshotPayloadBuilder;

@Service
public class LoadService {
	private Logger logger = LoggerFactory.getLogger(LoadService.class);
	
	private SnapshotManager snapshotManager = new SnapshotManager();

	@Autowired
	private RestTemplate restTemplate;
	
	private String postOutageToOchUrlString_DEV = "https://outage-dev.ameren.com/snapshot/process";
	private String postOutageToOchUrlString_QA = "https://outage-qa.ameren.com/snapshot/process";
	private static HttpHeaders QA_Header = new HttpHeaders();
	private boolean autoRun = false;

	private String env = null;
	private static int runCount = 1;

	public void sendToQA(String payload) {
		HttpEntity<String> postEntity = new HttpEntity<String>(payload, QA_Header);
		ResponseEntity<String> responseEntity = restTemplate.exchange(postOutageToOchUrlString_QA, HttpMethod.POST,
				postEntity, String.class);
	}

	public void sendToDev(String payload) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json");
		HttpEntity<String> postEntity = new HttpEntity<String>(payload, httpHeaders);
		ResponseEntity<String> responseEntity = restTemplate.exchange(postOutageToOchUrlString_DEV, HttpMethod.POST,
				postEntity, String.class);
	}

	public void runLoadTest() {
		String payload = getPayload(runCount);
		if (payload != null && payload.length() > 10) {
			runCount++;
			if(env != null && env.equalsIgnoreCase("DEV")) {
//				sendToDev(payload);
			}else if(env != null && env.equalsIgnoreCase("DEV")) {
//				sendToQA(payload);
			}
		}
	}

	public String getPayload(int whichRun) {
		Long currentSnapshotId = snapshotManager.getCurrentSnapshotId(whichRun);
//		populateSnapshot(currentSnapshotId, snapshotManager, transformerMap);		
//		try {
//			String payload = mapper.writeValueAsString(snapshotManager.getSnapshot(currentSnapshotId));
		String payload = snapshotManager.getSnapshotPayload(currentSnapshotId);
		logger.info("Get current snapshot: {} - {}", whichRun, currentSnapshotId);
		logger.info(payload);
		return payload;
//		} catch (JsonProcessingException e) {
//			System.out.println("Failed to getPayload test");
//			e.printStackTrace();
//		}
//		return null;
	}

	public boolean autoRun() {
		if (autoRun) {
			runLoadTest();
		}
		return autoRun;
	}

	public void buildData() {
		SnapshotPayloadBuilder builder = new SnapshotPayloadBuilder();
		this.snapshotManager = builder.build();
	}

	public void setAutoRun(boolean autoRun) {
		this.autoRun = autoRun;
	}

	public void setStartFrom(int count) {
		runCount = count;
	}

	public void setEnv(String env) {
		this.env  = env;
	}

}
