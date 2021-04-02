package com.ameren.outage.outageloadsimulator.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnapshotPayloadBuilder {
	private Logger logger = LoggerFactory.getLogger(SnapshotPayloadBuilder.class);
	private static final String ORDER_FILE = 
//			"src/main/resources/orderT2.json";
			"src/main/resources/order-detail-prod-0810-0814.json";
	private static final String DEVICE_FILE =
//			"src/main/resources/deviceT2.json";
			"src/main/resources/device-outage-prod-0810-0814-4.json";
	
	private ObjectMapper mapper = new ObjectMapper();
	private Map<Long, String> orderIdNumberMap = new HashMap<>();
	private Map<String, List<OrderPayload>> orderNumber_OrderList = new HashMap<>();
//	private Map<Long, List<DbDeviceOutageReader>> transformerMap = new HashMap<>();
	
	public SnapshotManager build() {
		logger.info("Start build payload");
		try {
			//TODO:
			SnapshotManager snapshotManager = new SnapshotManager(true);
			populateOrderDetails(snapshotManager);
			//order_detail_id, List of devices
//			Map<Long, List<DbDeviceOutageReader>> transformerMap = loadTransformers();
			loadTransformers();
			//TODO:
			checkOrderDevices();
			snapshotManager.buildSnapshots();
//			snapshotManager.setDevices(transformerMap);
			
			return snapshotManager;
		} catch (Exception e) {
			System.out.println("failed to load");
			e.printStackTrace();
			return null;
		}
	}

private void populateOrderDetails(SnapshotManager snapshotManager)
		throws IOException, JsonParseException, JsonMappingException {
	String json = new String(Files.readAllBytes(new File(ORDER_FILE).toPath()));
	DbOrders dbOrderList = mapper.readValue(json, DbOrders.class);

	logger.info("There are {} order details in order_detal table", dbOrderList.getOrders().size());
	for(DbOrderDetailReader dbOrder : dbOrderList.getOrders()) {
		OrderPayload orderPayload = OrderConverter.convert(dbOrder);
		SnapshotPayload snapshot = snapshotManager.getSnapshot(dbOrder.getSnapshot_id());
		if(dbOrder.getOrder_number() > 5000000) {
			orderPayload.setState("IL");
			snapshot.getState("IL").add(orderPayload);
		}else {
			snapshot.getState("MO").add(orderPayload);
			orderPayload.setState("MO");
		}
		buildOrderNumberMapOrderDetails(orderPayload);
	}
}
	
	private Map<Long, List<DbDeviceOutageReader>> loadTransformers() {
		//order detail id <-> list of transformers
		Map<Long, List<DbDeviceOutageReader>> transformers =new HashMap<>();
		
		try {
			String json = new String(Files.readAllBytes(new File(DEVICE_FILE).toPath()));
			List<DbDeviceOutageReader> dbTransformerList = mapper.readValue(json, new TypeReference<List<DbDeviceOutageReader>>(){});
			logger.info("There are {} device records", dbTransformerList.size());

			for(DbDeviceOutageReader dbTransformer : dbTransformerList) {
				List<DbDeviceOutageReader> list = transformers.get(dbTransformer.getOrder_detail_id());
				if(list == null) {
					list = new ArrayList<>();
					transformers.put(dbTransformer.getOrder_detail_id(), list);
				}
				list.add(dbTransformer);
				
				String orderNumber = orderIdNumberMap.get(dbTransformer.getOrder_detail_id());
				if(orderNumber != null) {
					for(OrderPayload orderPayload : orderNumber_OrderList.get(orderNumber)) {
						if(orderPayload.getSnapshotId()<= dbTransformer.getUpdated_snapshot_id()) {
							orderPayload.addDbDeviceOutageReader(dbTransformer);
						}
					}
					
				}else {
					logger.error("There is no order found for id: {}" ,dbTransformer.getOrder_detail_id());
				}			
			}

			//TODO:
//			checkOrderDevices();
			return transformers;
		} catch (Exception e) {
			logger.info("failed to prepare transformers");
			e.printStackTrace();
			return transformers;
		}
	}
	
	private void checkOrderDevices() {
		Set<Long> orderIds = new HashSet<>();
		for(List<OrderPayload> orders: orderNumber_OrderList.values()) {
			for(OrderPayload order:orders) {
				if(order.getDevices().isEmpty()) {
					orderIds.add(order.getId());
				}
			}
		}

		if(orderIds.size() > 0) {
			List<Long> sortedOrderids = new ArrayList(orderIds);
			Collections.sort(sortedOrderids);
			StringBuffer sb = new StringBuffer();
			for(Long id: sortedOrderids) {
				sb.append(id).append(",");
			}
			logger.warn("There are {} order details without transformers: {}", orderIds.size(), sb.toString());
		}
	}
	
	private void buildOrderNumberMapOrderDetails(OrderPayload orderPayload) {
		orderIdNumberMap.put(orderPayload.getId(), orderPayload.getOrderNumber());
		List<OrderPayload> list = orderNumber_OrderList.get(orderPayload.getOrderNumber());
		if(list ==null) {
			list = new ArrayList<>();
			orderNumber_OrderList.put(orderPayload.getOrderNumber(), list);
		}
		list.add(orderPayload);
	}
}
