package com.example.demo.multichannel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestChannels {
	
	String OUTPUT_CHANNEL_1="outputChannel1";
	String OUTPUT_CHANNEL_2="outputChannel2";

	@Output(OUTPUT_CHANNEL_1)
	MessageChannel outputChannel1();
	
	@Output(OUTPUT_CHANNEL_2)
	MessageChannel outputChannel2();

}
