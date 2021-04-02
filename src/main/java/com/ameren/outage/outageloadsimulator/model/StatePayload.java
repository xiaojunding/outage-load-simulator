package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatePayload implements Serializable{

//	private final Logger logger = LogManager.getLogger(State.class);

//	@JsonProperty("state")
	private String state;
//	@JsonProperty("orders")
	private List<OrderPayload> orders = new ArrayList<OrderPayload>();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<OrderPayload> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderPayload> orders) {
		this.orders = orders;
	}

	public void add(OrderPayload orderPayload) {
		orders.add(orderPayload);
	}

//	@Override
//	public String toString() {
//		ObjectMapper jsonMapper = new ObjectMapper();
//		try {
//			return jsonMapper.writeValueAsString(this);
//		} catch (JsonProcessingException jsonProcessingException) {
//			logger.error(String.format("Could not marshall State into Json. Error: ",
//					new ExceptionAnalyzer().getCauseRecursively(jsonProcessingException).getMessage()));
//			logger.error(jsonProcessingException);
//			return jsonProcessingException.getMessage();
//		}
//	}
//}
}
