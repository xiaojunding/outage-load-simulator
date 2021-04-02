package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnapshotPayload implements Serializable{
	
	@JsonProperty("states")
	private List<StatePayload> states;
	@JsonIgnore
	private StatePayload mo;
	@JsonIgnore
	private StatePayload il;
	
	public SnapshotPayload() {
		mo = new StatePayload();
		mo.setState("MO");
		
		il = new StatePayload();
		il.setState("IL");
		
		states = new ArrayList<StatePayload>();
		states.add(il);
		states.add(mo);
	}
	
	@JsonIgnore
	public StatePayload getState(String stateName) {
		return stateName.equalsIgnoreCase("IL")? il:mo;
	}
	
	public List<StatePayload> getStates(){
		return states;
	}

}
