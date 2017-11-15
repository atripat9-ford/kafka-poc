package com.example.demo.sinkAndSource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(SinkAndSourceChannels.class)
public class MSLJsonMessageReceiver {
	
	@Autowired
	ObjectMapper mapper;
	
	//@ServiceActivator(inputChannel=SinkAndSourceChannels.INPUT_CHANNEL_1)
    public void listenToChannel1(GenericMessage<String> message) throws IOException {
		System.out.println("Received Generic messsage: " + message);
		
		String payload = message.getPayload();

		System.out.println("Received payload messsage: " + payload);
		
		
	}

}
