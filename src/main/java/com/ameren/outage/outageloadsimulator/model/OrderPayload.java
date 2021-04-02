package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderPayload implements Serializable{
	
	@JsonIgnore
	private long id;
    private String orderNumber;
    private String outageStartDate;
    private int outageSize;
    private String outageStatus;
    private String causeCode;
    private String causeCodeDescription;
    private String esrt;
    private String crewIconIndicator;
    private boolean hasCall;
    private String operatingCenter;
    private boolean communicateToOC;
    private boolean communicateAutoEsrt;
    private boolean autoEsrt;
    private String premiseNumber;
    private List<String> transformers;
    private String state;
    @JsonIgnore
	private List<DbDeviceOutageReader> devices = new ArrayList();
    @JsonIgnore
    private long snapshotId;
    
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOutageStartDate() {
		return outageStartDate;
	}
	public void setOutageStartDate(String outageStartDate) {
		this.outageStartDate = outageStartDate;
	}
	public int getOutageSize() {
		return outageSize;
	}
	public void setOutageSize(int outageSize) {
		this.outageSize = outageSize;
	}
	public String getOutageStatus() {
		return outageStatus;
	}
	public void setOutageStatus(String outageStatus) {
		this.outageStatus = outageStatus;
	}
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getCauseCodeDescription() {
		return causeCodeDescription;
	}
	public void setCauseCodeDescription(String causeCodeDescription) {
		this.causeCodeDescription = causeCodeDescription;
	}
	public String getEsrt() {
		return esrt;
	}
	public void setEsrt(String esrt) {
		this.esrt = esrt;
	}
	public String getCrewIconIndicator() {
		return crewIconIndicator;
	}
	public void setCrewIconIndicator(String crewStatus) {
		this.crewIconIndicator = crewStatus;
	}
	public boolean isHasCall() {
		return hasCall;
	}
	public void setHasCall(boolean hasCall) {
		this.hasCall = hasCall;
	}
	public String getOperatingCenter() {
		return operatingCenter;
	}
	public void setOperatingCenter(String operatingCenter) {
		this.operatingCenter = operatingCenter;
	}
	public boolean isCommunicateToOC() {
		return communicateToOC;
	}
	public void setCommunicateToOC(boolean communicateToOC) {
		this.communicateToOC = communicateToOC;
	}
	public boolean isCommunicateAutoEsrt() {
		return communicateAutoEsrt;
	}
	public void setCommunicateAutoEsrt(boolean communicateAutoEsrt) {
		this.communicateAutoEsrt = communicateAutoEsrt;
	}
	public boolean isAutoEsrt() {
		return autoEsrt;
	}
	public void setAutoEsrt(boolean autoEsrt) {
		this.autoEsrt = autoEsrt;
	}
	public String getPremiseNumber() {
		return premiseNumber;
	}
	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}
	public List<String> getTransformers() {
		return transformers;
	}
	public void setTransformers(List<String> transformers) {
		this.transformers = transformers;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public long getSnapshotId() {
		return snapshotId;
	}
	public void setSnapshotId(long snapshotId) {
		this.snapshotId = snapshotId;
	}
//	@JsonIgnore
//	public void setDbDeviceOutageReaderList(List<DbDeviceOutageReader> transformersByOrderId) {
//		this.devices = transformersByOrderId;
//	}
	@JsonIgnore
	public void addDbDeviceOutageReader(DbDeviceOutageReader device) {
		this.devices.add(device);
	}
	@JsonIgnore
	public List<DbDeviceOutageReader> getDevices(){
		return devices;
	}

	@JsonIgnore
	public void populateTransformers(long currentSnapshotId) {
		List<String> transformers = new ArrayList<>();
		String premiseNumber = null;
		boolean premiseFound = false;
		for(DbDeviceOutageReader transformer: devices) {			
			if(transformer.getFirst_snapshot_id() <= currentSnapshotId && 
					transformer.getLast_snapshot_id() >= currentSnapshotId) {
				transformers.add(transformer.getTransformer_number());
				if(transformer.getPremise_number() != null) {
					if(premiseFound) {
						System.out.println("Error: multiple premise found: " + id);
					}else {
						premiseNumber = transformer.getPremise_number();
						premiseFound = true;	
					}
				}
			}
		}
		this.setPremiseNumber(premiseNumber);
		this.setTransformers(transformers);
	}    

	
}
