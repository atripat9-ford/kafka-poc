package com.example.demo.multichannel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableBinding(TestChannels.class)
@ComponentScan(basePackages = {"com.example.demo.multichannel"})
@Profile("TestChannelApplication")
public class TestOutputChannelApplication{

	public static void main(String[] args) {
		SpringApplication.run(TestOutputChannelApplication.class, args);
	}

}
