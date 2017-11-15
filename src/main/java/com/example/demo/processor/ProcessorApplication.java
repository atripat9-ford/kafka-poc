package com.example.demo.processor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.SendTo;

import com.example.demo.TestChannels;


@SpringBootApplication
@EnableBinding(TestChannels.class)
@Profile("Processor")
public class ProcessorApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(ProcessorApplication.class, args);
//	}
	
	@StreamListener(TestChannels.INPUT_CHANNEL_1)
	@SendTo({TestChannels.OUTPUT_CHANNEL_1})
	public String publishChannel1(String sinkTimeInfo) {
		System.out.println("Received on channel 1: " + sinkTimeInfo);
		return sinkTimeInfo;
	}
	
}
