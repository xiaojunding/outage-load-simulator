package com.ameren.outage.outageloadsimulator.model;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnapshotManager{
	private Logger logger = LoggerFactory.getLogger(SnapshotManager.class);
	private Map<Long, SnapshotPayload> snapshots = new HashMap<>();
	private List<Long> snapshotIds = new ArrayList<>();
	private Map<Long, List<DbDeviceOutageReader>> transformerMap = new HashMap<>();
	private ObjectMapper mapper = new ObjectMapper();
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Map<Long, String> snapshotPayloadMap = new HashMap<>();
	private static final String PAYLOAD_FILE = "src/main/resources/calculated-snapshot-payload.json";
	private static final String PAYLOAD_FILE_SAve = "src/main/resources/new-calculated-snapshot-payload.json";
	public SnapshotManager(){
			snapshotPayloadMap = loadFromExistingCalculatedPayloads();
			snapshotIds = new ArrayList<>(snapshotPayloadMap.keySet());
			Collections.sort(snapshotIds);
	}
	
	public SnapshotManager(boolean setup){
	}
	
	private Map<Long, String> loadFromExistingCalculatedPayloads() {

		try {
			String json = new String(Files.readAllBytes(new File(PAYLOAD_FILE).toPath()));
			return mapper.readValue(json, new TypeReference<Map<Long, String>>() {
			});
		} catch (IOException e) {
			logger.info("Failed to preloaded snapshot payloads");
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	private void saveForFuture() {
		try {
			String json = mapper.writeValueAsString(snapshotPayloadMap);
//			Path path = Paths.get(PAYLOAD_FILE_SAve);
//		    byte[] strToBytes = json.getBytes();
//
//		    Files.write(path, strToBytes);
			
			    FileOutputStream fos = new FileOutputStream("new-calculated-snapshot-payload.json");
			    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
			    outStream.writeUTF(json);
			    outStream.close();

			    // verify the results
//			    String result;
//			    FileInputStream fis = new FileInputStream(fileName);
//			    DataInputStream reader = new DataInputStream(fis);
//			    result = reader.readUTF();
//			    reader.close();
		    logger.info("Save to file successfully");
		} catch (IOException e) {
			logger.info("Failed to save snapshot payloads");
			e.printStackTrace();
		}
		
	}
	public SnapshotPayload getSnapshot(long snapshotId){
		if(snapshots.get(snapshotId) == null) {
			SnapshotPayload snapshot = new SnapshotPayload();
			snapshots.put(snapshotId, snapshot);
		}
		return snapshots.get(snapshotId);
	}
	
	public String getSnapshotPayload(long snapshotId){
		return snapshotPayloadMap.get(snapshotId);
	}
	
//	public SnapshotPayload getSnapshot(int runCount) {
//		return getSnapshot(runSnapshotIdsMap.get(runCount));
//		
//	}

	public Map<Long, SnapshotPayload> getSnapshots() {
		return snapshots;
	}
	
	public void buildSnapshotIds(){
		traverseSnapshots();
		
		Set<Long> keySet = snapshots.keySet();
		this.snapshotIds = new ArrayList<>(keySet);
		Collections.sort(snapshotIds);
		for(int i = 0; i < snapshotIds.size(); i++) {
			logger.info("run {} - {}", i+1, snapshotIds.get(i));
		}
		logger.info("There are {} snapshots", this.snapshotIds.size()) ;
	}
	
	public void buildSnapshots() {		
//		traverseSnapshots();
//		
//		Set<Long> keySet = snapshots.keySet();
//		this.snapshotIds = new ArrayList<>(keySet);
//		Collections.sort(snapshotIds);
//		for(int i = 0; i < snapshotIds.size(); i++) {
//			logger.info("run {} - {}", i+1, snapshotIds.get(i));
//		}
//		logger.info("There are {} snapshots", this.snapshotIds.size()) ;
		buildSnapshotIds();
		generateSnapshotPayloads();
	}

	private void traverseSnapshots() {
		Set<Long> keySet = snapshots.keySet();
		List<Long> snapshotIdsWithOrderDetails = new ArrayList<>(keySet);
		Collections.sort(snapshotIdsWithOrderDetails);
		long start = snapshotIdsWithOrderDetails.get(0);
		long end = snapshotIdsWithOrderDetails.get(snapshotIdsWithOrderDetails.size()-1);
		
		for(long i = start; i <= end; i++) {
			getSnapshot(i);
		}
	}
	//TODO: to be removed
	public List<Long> getSnapshotIds() {
//		Set<Long> keySet = snapshots.keySet();
//		ArrayList<Long> snapshotIds = new ArrayList<>(keySet);
//		Collections.sort(snapshotIds);
//		currentSnapshotId = snapshotIds.get(0);
		return snapshotIds;
	}

//	public Long getCurrentSnapshotId() {
//		return currentSnapshotId;
//	}

	public Long getCurrentSnapshotId(int runCount) {
		return snapshotIds.get(runCount - 1);
	}
//	
//	public void increaseCurrentSnapshotId() {
//		currentSnapshotId++;
//	}

	public void setDevices(Map<Long, List<DbDeviceOutageReader>> transformerMap) {
		this.transformerMap = transformerMap;
		executor.submit(() -> {
		    for(Long id: snapshotIds) {
		    	populateSnapshot(id);
		    }
		    saveForFuture();
		});
	}
	
	public void generateSnapshotPayloads() {
		executor.submit(() -> {
		    for(Long id: snapshotIds) {
		    	populateSnapshot(id);
		    }
//		    saveForFuture();
		});
	}


//	public void setup() {
//		executor.submit(() -> {
//		    for(Long id: snapshotIds) {
//		    	populateSnapshot(id);
//		    }				
//		});
//	}
	
//	private void populateSnapshot(long currentSnapshotId, SnapshotManager snapshotManager,
//			Map<Long, List<DbDeviceOutageReader>> transformerMap) {
	private void populateSnapshot(long currentSnapshotId) {
//		System.out.println("Populating snapshot ID: " + currentSnapshotId);
		logger.info("Populating snapshot {}", currentSnapshotId);
		
//		SnapshotPayload previousSnapshotPayload = snapshotManager.getSnapshot(currentSnapshotId-1);
//		SnapshotPayload currentSnapshot = snapshotManager.getSnapshot(currentSnapshotId);
		
		SnapshotPayload previousSnapshotPayload = getSnapshot(currentSnapshotId-1);
		SnapshotPayload currentSnapshot = getSnapshot(currentSnapshotId);
		
		for(StatePayload state: currentSnapshot.getStates()) {
			//find all the order numbers in current snapshot
			Set<String> currentSnapshotOrderNumbers = new HashSet<>();
			for(OrderPayload orderPayload: state.getOrders()) {
				currentSnapshotOrderNumbers.add(orderPayload.getOrderNumber());
			}
			
			//add previous orders which are not present in current
			if(previousSnapshotPayload != null) {
				List<OrderPayload> previousOrders = previousSnapshotPayload.getState(state.getState()).getOrders();
				for(OrderPayload order: previousOrders) {
					if(!currentSnapshotOrderNumbers.contains(order.getOrderNumber())) {
						order.setTransformers(null);
						order.setPremiseNumber(null);
						state.getOrders().add(order);
					}

				}
			}
			
			//go over each order
			Set<OrderPayload> toBeRemoved = new HashSet<>();
			for(OrderPayload orderPayload: state.getOrders()) {
//				List<DbDeviceOutageReader> transformersByOrderId = transformerMap.get(orderPayload.getId());
//				if(transformersByOrderId != null && !transformersByOrderId.isEmpty()) {
//					orderPayload.setDbDeviceOutageReaderList(transformersByOrderId);
					orderPayload.populateTransformers(currentSnapshotId);
//				}else {
//					System.out.println("Initial Order Detail does not have transformers: " + orderPayload.getId());
////					secondTry(currentSnapshotId, orderPayload, snapshotManager);
//				}
				if(orderPayload.getTransformers() == null || orderPayload.getTransformers().isEmpty()) {
					toBeRemoved.add(orderPayload);
				}
			}
			
			if(!toBeRemoved.isEmpty()) {
				state.getOrders().removeAll(toBeRemoved);
			}
			
//			StringBuffer sb = new StringBuffer();
//			for(OrderPayload order :state.getOrders()) {
//				sb.append(order.getOrderNumber()).append(",");
//			}
			logger.info("There are {} Orders in snapshot {} for {}", state.getOrders().size(), currentSnapshotId, state.getState());
		}
		try {
			String payload = mapper.writeValueAsString(getSnapshot(currentSnapshotId));
			snapshotPayloadMap.put(currentSnapshotId, payload);
			logger.info(snapshotPayloadMap.get(currentSnapshotId));
		} catch (JsonProcessingException e) {
			logger.error("Failed to generate payload for {}", currentSnapshotId);
			e.printStackTrace();
		}
	}
	
}
