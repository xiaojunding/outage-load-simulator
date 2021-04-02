package com.ameren.outage.outageloadsimulator;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class EmpathyCommunication implements Serializable{

	private String id;
	private Set<String> orderNumbers;
	
	public EmpathyCommunication(String snapshotId, Set<String> orderNumbers) {
		this.id = snapshotId;
		this.orderNumbers = orderNumbers;
	}

	public String getSnapshotId() {
		return id;
	}

	public Set<String> getOrderNumbers() {
		return orderNumbers;
	}	
}
