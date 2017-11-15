package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestChannels {
	
	String INPUT_CHANNEL_1="inputChannel1";
	String OUTPUT_CHANNEL_1="outputChannel1";
	String OUTPUT_CHANNEL_2="outputChannel2";

	@Input(INPUT_CHANNEL_1)
	MessageChannel inputChannel1();
	
	@Output(OUTPUT_CHANNEL_1)
	MessageChannel outputChannel1();
	
	@Output(OUTPUT_CHANNEL_2)
	MessageChannel outputChannel2();

}
