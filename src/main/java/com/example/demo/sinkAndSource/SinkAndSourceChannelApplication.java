package com.example.demo.sinkAndSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBinding(SinkAndSourceChannels.class)
@ComponentScan(basePackages = {"com.example.demo.sinkAndSource"})
public class SinkAndSourceChannelApplication{

	public static void main(String[] args) {
		SpringApplication.run(SinkAndSourceChannelApplication.class, args);
	}

}
