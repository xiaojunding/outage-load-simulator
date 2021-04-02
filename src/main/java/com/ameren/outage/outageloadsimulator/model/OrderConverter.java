package com.ameren.outage.outageloadsimulator.model;

import java.util.List;

public class OrderConverter {

//	 private String orderNumber;
//	    private String outageStartDate;
//	    private int outageSize;
//	    private String outageStatus;
//	    private String causeCode;
//	    private String causeCodeDescription;
//	    private String esrt;
//	    private String crewStatus;
//	    private boolean hasCall;
//	    private String operatingCenter;
//	    private boolean communicateToOC;
//	    private boolean communicateAutoEsrt;
//	    private boolean autoEsrt;
//	    private String premiseNumber;
//	    private List<String> transformers;
	public static OrderPayload convert(DbOrderDetailReader r) {
		
		OrderPayload od = new OrderPayload();
		od.setOrderNumber(String.valueOf(r.getOrder_number()));
		od.setOutageStartDate(r.getOutage_start_date());
		od.setOutageSize(r.getOutage_size());
		od.setOutageStatus(r.getOutage_status());
		od.setCauseCode(r.getCause_code());
		od.setCauseCodeDescription(r.getCause_code_description());
		od.setEsrt(r.getEsrt());
		od.setCrewIconIndicator(r.getCrew_status());;
		od.setHasCall(r.getHas_call() == 1? true:false);
		od.setOperatingCenter(r.getOperating_center());
		od.setCommunicateToOC(r.getCommunicate_to_oc() == 1? true: false);
		od.setCommunicateAutoEsrt(r.getCommunicate_auto_esrt() ==1? true:false);
		od.setAutoEsrt(r.getAuto_esrt() == 1? true:false);
		
		od.setSnapshotId(r.getSnapshot_id());
		od.setId(r.getId());
		return od;
	    
	}
}
