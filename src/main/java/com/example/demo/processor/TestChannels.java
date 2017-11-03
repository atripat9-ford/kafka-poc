package com.example.demo.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TestChannels {
	
	String INPUT_CHANNEL_1="inputChannel1";
	String INPUT_CHANNEL_2="inputChannel2";
	String OUTPUT_CHANNEL_1="outputChannel1";
	String OUTPUT_CHANNEL_2="outputChannel2";

	@Input(INPUT_CHANNEL_1)
	SubscribableChannel inputChannel1();
	
	@Input(INPUT_CHANNEL_2)
	SubscribableChannel inputChannel2();
	
	@Output(OUTPUT_CHANNEL_1)
	MessageChannel outputChannel1();
	
	@Output(OUTPUT_CHANNEL_2)
	MessageChannel outputChannel2();

}
