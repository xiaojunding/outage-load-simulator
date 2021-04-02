package com.ameren.outage.outageloadsimulator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DbTransformers implements Serializable{
	private List<DbDeviceOutageReader> transformers = new ArrayList<>();

	public List<DbDeviceOutageReader> getTransformers() {
		return transformers;
	}

	public void setTransformers(List<DbDeviceOutageReader> transformers) {
		this.transformers = transformers;
	}
	
	
}
