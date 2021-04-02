package com.ameren.outage.outageloadsimulator;

import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class Payload {
	private String id;
	private String snapshotId;
	private String payload;
	
	public Payload(String id, String snapshotId, String payload) {
		this.id = id;
		this.snapshotId = snapshotId;
		this.payload = payload;
	}

	public String getId() {
		return id;
	}

	public String getSnapshotId() {
		return snapshotId;
	}

	public String getPayload() {
		return payload;
	}

}
