package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderWrapper implements Serializable{

	private DbOrderDetailReader dbOrder;
	public OrderWrapper(DbOrderDetailReader dbOrder) {
		this.dbOrder = dbOrder;
	}
	
//	@JsonProperty("order_number")
//	private String orderNumber;
//	@JsonProperty("outage_start_date")
//	private String outageStartDate;
//	@JsonProperty("outage_size")
//	private int outageSize;
//	@JsonProperty("outage_status")
//	private String outageStatus;
//	@JsonProperty("cause_code")
//	private String causeCode;
//	@JsonProperty("cause_code_description")
//	private String causeCodeDescription;
//    @JsonFormat(timezone = "UTC")
//    @JsonProperty("esrt")
//    private String esrt;
//    @JsonProperty("crew_status")
//    private String crewStatus;
//    @JsonProperty("has_call")
//    private boolean hasCall;
//    @JsonProperty("operating_center")
//    private String operatingCenter;
//    @JsonProperty("communicate_to_oc")
//    private boolean communicateToOC;
//    @JsonProperty("communicate_auto_esrt")
//    private boolean communicateAutoEsrt;
//    @JsonProperty("auto_esrt")
//    private boolean autoEsrt;
//    @JsonProperty("premiseNumber")
//    private String premiseNumber;
//    @JsonProperty("transformers")
//    private List<String> transformers;
    
    
	public DbOrderDetailReader getDbOrder() {
		return dbOrder;
	}
	@JsonProperty("orderNumber")
	public String getOrderNumber() {
		return String.valueOf(dbOrder.getOrder_number());
	}
	
	public String getOutageStartDate() {
		return dbOrder.getOutage_start_date();
	}
	public int getOutageSize() {
		return dbOrder.getOutage_size();
	}
	public String getOutageStatus() {
		return dbOrder.getOutage_status();
	}
	public String getCauseCode() {
		return dbOrder.getCause_code();
	}
	public String getCauseCodeDescription() {
		return dbOrder.getCause_code_description();
	}
	public String getEsrt() {
		return dbOrder.getEsrt();
	}
	public String getCrewStatus() {
		return dbOrder.getCrew_status();
	}
	public boolean isHasCall() {
		return dbOrder.getHas_call() == 1? true:false;
	}
	public String getOperatingCenter() {
		return dbOrder.getOperating_center();
	}
	public boolean isCommunicateToOC() {
		return dbOrder.getCommunicate_to_oc() == 1? true:false;
	}
	public boolean isCommunicateAutoEsrt() {
		return dbOrder.getCommunicate_auto_esrt() == 1? true:false;
	}
	public boolean isAutoEsrt() {
		return dbOrder.getAuto_esrt() == 1? true: false;
	}
//	public String getPremiseNumber() {
//		return premiseNumber;
//	}
//	public List<String> getTransformers() {
//		return transformers;
//	}
//	public void setPremiseNumber(String premiseNumber) {
//		this.premiseNumber = premiseNumber;
//	}
//	public void setTransformers(List<String> transformers) {
//		this.transformers = transformers;
//	}
    
	
//    private String orderNumber;
//    private Timestamp outageStartDate;
//    private int outageSize;
//    private String outageStatus;
//    private String causeCode;
//    private String causeCodeDescription;
//    @JsonFormat(timezone = "UTC")
//    private Timestamp esrt;
//    private String crewStatus;
//    private boolean hasCall;
//    private String operatingCenter;
//    private boolean communicateToOC;
//    private boolean communicateAutoEsrt;
//    private boolean autoEsrt;
//    private String premiseNumber;
//    private List<String> transformers;
//    private boolean isFirstCause;
//    
//    "orderNumber": "5948187",
//    "outageStartDate": "2021-03-15T12:49:54-05",
//    "outageSize": 1,
//    "outageStatus": "Order has been received",
//    "causeCode": "UN",
//    "causeCodeDescription": "Undetermined",
//    "esrt": null,
//    "crewIconIndicator": null,
//    "hasCall": true,
//    "operatingCenter": "127",
//    "communicateToOC": true,
//    "communicateAutoEsrt": true,
//    "autoEsrt": true,
//    "premiseNumber": 611100,
//    "transformers": [
//        "23010152382"
//    ]
	
}
