package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbOrderDetailReader implements Serializable{

	@JsonProperty("id")
	private long id;  
	@JsonProperty("order_number")
	private long order_number;
	@JsonProperty("outage_start_date")
	private String outage_start_date;
	@JsonProperty("outage_size")
	private int outage_size;
	@JsonProperty("outage_status")
	private String outage_status;
	@JsonProperty("cause_code")
	private String cause_code;
	@JsonProperty("cause_code_description")
	private String cause_code_description;
	@JsonProperty("esrt")
	private String esrt;
//	@JsonProperty("")
//	private int is_frozen;
	@JsonProperty("crew_status")
	private String crew_status;
	@JsonProperty("has_call")
	private int has_call;
	@JsonProperty("operating_center")
	private String operating_center;
	@JsonProperty("communicate_to_oc")
	private int communicate_to_oc;
	@JsonProperty("communicate_auto_esrt")
	private int communicate_auto_esrt;
	@JsonProperty("auto_esrt")
	private int auto_esrt;
	@JsonProperty("snapshot_id")
	private long snapshot_id;
	@JsonIgnore
	private String state;
	
	
	//Getters
//	"orderNumber"
//    "outageStartDate"
//    "outageSize"
//    "outageStatus"
//    "causeCode"
//    "causeCodeDescription"
//    "esrt"
//    "crewIconIndicator"
//    "hasCall"
//    "operatingCenter"
//    "communicateToOC"
//    "communicateAutoEsrt"
//    "autoEsrt"
//    "premiseNumber"
//    "transformers"
	/*
	@JsonProperty("orderNumber")
	public String getOrder_number() {
		return String.valueOf(order_number);
	}
	@JsonProperty("outageStartDate")
	public String getOutage_start_date() {
		return outage_start_date;
	}
	@JsonProperty("outageSize")
	public int getOutage_size() {
		return outage_size;
	}
	@JsonProperty("outageStatus")
	public String getOutage_status() {
		return outage_status;
	}
	@JsonProperty("causeCode")
	public String getCause_code() {
		return cause_code;
	}
	@JsonProperty("causeCodeDescription")
	public String getCause_code_description() {
		return cause_code_description;
	}
	@JsonProperty("esrt")
	public String getEsrt() {
		return esrt;
	}
	@JsonProperty("crewIconIndicator")
	public String getCrew_status() {
		return crew_status;
	}
	@JsonProperty("hasCall")
	public int getHas_call() {
		return has_call;
	}
	@JsonProperty("operatingCenter")
	public String getOperating_center() {
		return operating_center;
	}
	@JsonProperty("")
	public String getState() {
		return state;
	}
	@JsonProperty("communicateToOC")
	public int getCommunicate_to_oc() {
		return communicate_to_oc;
	}
	@JsonProperty("communicateAutoEsrt")
	public int getCommunicate_auto_esrt() {
		return communicate_auto_esrt;
	}
	@JsonProperty("autoEsrt")
	public int getAuto_esrt() {
		return auto_esrt;
	}
	@JsonIgnore
	public long getSnapshot_id() {
		return snapshot_id;
	}
	*/
	//Setters
	public void setOrder_number(long order_number) {
		this.order_number = order_number;
	}
	public void setOutage_start_date(String outage_start_date) {
		this.outage_start_date = DateConverter.convert(outage_start_date);
	}
	public void setOutage_size(int outage_size) {
		this.outage_size = outage_size;
	}
	public void setOutage_status(String outage_status) {
		this.outage_status = outage_status;
	}
	public void setCause_code(String cause_code) {
		this.cause_code = cause_code;
	}
	public void setCause_code_description(String cause_code_description) {
		this.cause_code_description = cause_code_description;
	}
	public void setEsrt(String esrt) {
		this.esrt = DateConverter.convert(esrt);
	}
	public void setCrew_status(String crew_status) {
		this.crew_status = crew_status;
	}
	public void setHas_call(int has_call) {
		this.has_call = has_call;
	}
	public void setOperating_center(String operating_center) {
		this.operating_center = operating_center;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCommunicate_to_oc(int communicate_to_oc) {
		this.communicate_to_oc = communicate_to_oc;
	}
	public void setCommunicate_auto_esrt(int communicate_auto_esrt) {
		this.communicate_auto_esrt = communicate_auto_esrt;
	}
	public void setAuto_esrt(int auto_esrt) {
		this.auto_esrt = auto_esrt;
	}
	public void setSnapshot_id(long snapshot_id) {
		this.snapshot_id = snapshot_id;
	}
	public long getOrder_number() {
		return order_number;
	}
	public String getOutage_start_date() {
		return outage_start_date;
	}
	public int getOutage_size() {
		return outage_size;
	}
	public String getOutage_status() {
		return outage_status;
	}
	public String getCause_code() {
		return cause_code;
	}
	public String getCause_code_description() {
		return cause_code_description;
	}
	public String getEsrt() {
		return esrt;
	}
	public String getCrew_status() {
		return crew_status;
	}
	public int getHas_call() {
		return has_call;
	}
	public String getOperating_center() {
		return operating_center;
	}
	public int getCommunicate_to_oc() {
		return communicate_to_oc;
	}
	public int getCommunicate_auto_esrt() {
		return communicate_auto_esrt;
	}
	public int getAuto_esrt() {
		return auto_esrt;
	}
	public long getSnapshot_id() {
		return snapshot_id;
	}
	public String getState() {
		return state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

	
	

}
