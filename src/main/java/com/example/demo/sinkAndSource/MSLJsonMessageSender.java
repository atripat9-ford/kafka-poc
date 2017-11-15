package com.example.demo.sinkAndSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(SinkAndSourceChannels.class)
public class MSLJsonMessageSender {

	@Autowired
	private SinkAndSourceChannels output;
		
	@Autowired
	ObjectMapper mapper;
	
	  public void sendCar(Car car, String contentType) throws JsonProcessingException {
	    	
		    String payload = mapper.writeValueAsString(car);
	    	Message<?> message =  MessageBuilder.withPayload(payload)
	    			.setHeader(MessageHeaders.CONTENT_TYPE, contentType)
	    			.build();    	
	        
	        output.outputChannel1().send(message); 
	        output.outputChannel2().send(message); 
	   }
}
