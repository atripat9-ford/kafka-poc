package com.example.demo.sinkAndSource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseFPEvent {
	private final String eventType;
	private final String eventId;
	private final String userGuid;
	private final String vin;
	private final String originator;	
	private final String publisher;
	private final String originationTimestamp;
	
	public String getEventType(){
		return eventType;
	}
	public String getEventId(){
		return eventId;
	}
	public String getUserGuid(){
		return userGuid;
	}
	
	public String getVin(){
		return vin;
	}
	
	public String getOriginator(){
		return originator;
	}
	public String getPublisher(){
		return publisher;
	}
	public String getOriginationTimestamp(){
		return originationTimestamp;
	}
	
	@JsonCreator
	public BaseFPEvent (@JsonProperty("eventType") String eventType, @JsonProperty("eventId") String eventId,
				@JsonProperty("userGuid") String userGuid, @JsonProperty("vin") String vin,
				@JsonProperty("originator") String originator, @JsonProperty("publisher") String publisher,
				@JsonProperty("originationTimestamp") String originationTimestamp)
	{
		this.eventType = eventType;
		this.eventId = eventId;
		this.userGuid = userGuid;
		this.vin= vin;
		this.originator = originator;	
		this.publisher = publisher;
		this.originationTimestamp = originationTimestamp;
		
	}

}
