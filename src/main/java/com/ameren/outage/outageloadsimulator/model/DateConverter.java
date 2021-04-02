package com.ameren.outage.outageloadsimulator.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateConverter {
	
	private static  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX");
	
	public static String convert(String timestamp) {
		if(timestamp == null) {
			return null;
		}
		LocalDateTime now = LocalDateTime.now();
		Timestamp tm = Timestamp.valueOf(now.plusDays(4));
		return df.format(tm);
	}

}
