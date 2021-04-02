package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbOrders implements Serializable{
//	@JsonProperty("orders")
//	private List<OrderDetail> orders;
//
//	public List<OrderDetail> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<OrderDetail> orders) {
//		this.orders = orders;
//	}

	@JsonProperty("orders")
	private List<DbOrderDetailReader> orders;

	public List<DbOrderDetailReader> getOrders() {
		return orders;
	}

	public void setOrders(List<DbOrderDetailReader> orders) {
		this.orders = orders;
	}
	

}
