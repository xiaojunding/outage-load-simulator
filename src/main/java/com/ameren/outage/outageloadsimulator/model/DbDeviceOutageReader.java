package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbDeviceOutageReader implements Serializable {
	
//	"id" : 835649,
	private String transformer_number;
	private String premise_number;
	private long order_detail_id;
	private long last_snapshot_id;
	private long first_snapshot_id;
	private long updated_snapshot_id;
	private String device_outage_status;
	public String getTransformer_number() {
		return transformer_number;
	}
	public void setTransformer_number(String transformer_number) {
		this.transformer_number = transformer_number;
	}
	public String getPremise_number() {
		return premise_number;
	}
	public void setPremise_number(String premise_number) {
		this.premise_number = premise_number;
	}
	public long getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(long order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	public long getLast_snapshot_id() {
		return last_snapshot_id;
	}
	public void setLast_snapshot_id(long last_snapshot_id) {
		this.last_snapshot_id = last_snapshot_id;
	}
	public long getFirst_snapshot_id() {
		return first_snapshot_id;
	}
	public void setFirst_snapshot_id(long first_snapshot_id) {
		this.first_snapshot_id = first_snapshot_id;
	}
	public String getDevice_outage_status() {
		return device_outage_status;
	}
	public void setDevice_outage_status(String device_outage_status) {
		this.device_outage_status = device_outage_status;
	}
	public long getUpdated_snapshot_id() {
		return updated_snapshot_id;
	}
	public void setUpdated_snapshot_id(long updated_snapshot_id) {
		this.updated_snapshot_id = updated_snapshot_id;
	}
	
	
//	"device_outage_status" : "DEVICE_RESTORED",
//	"first_snapshot_id" : 75210,
//	"last_snapshot_id" : 75228,
//	"updated_snapshot_id" : 75227,
//	"moved_to_device_outage_id" : -1,
//	"state" : "MO",

	
}
