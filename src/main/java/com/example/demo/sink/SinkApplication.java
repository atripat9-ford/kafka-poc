package com.example.demo.sink;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;

import com.example.demo.TestChannels;


@SpringBootApplication
@EnableBinding(TestChannels.class)
@Profile("sink")
public class SinkApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(SinkApplication.class, args);
//	}
	
	@StreamListener(TestChannels.INPUT_CHANNEL_1)
	public void loggerSink(String sinkTimeInfo) {
		System.out.println("Received: " + sinkTimeInfo);
	}
}
