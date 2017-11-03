package com.example.demo.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SinkChannels {
	
	String INPUT_CHANNEL_1="inputChannel1";
	String INPUT_CHANNEL_2="inputChannel2";

	@Input(INPUT_CHANNEL_1)
	SubscribableChannel inputChannel1();
	
	@Input(INPUT_CHANNEL_2)
	SubscribableChannel inputChannel2();

}
