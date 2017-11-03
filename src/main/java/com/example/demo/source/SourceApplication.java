package com.example.demo.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.multichannel.TestChannels;

@SpringBootApplication
@EnableBinding(TestChannels.class)
@ComponentScan(basePackages = {"com.example.demo.source"})
public class SourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SourceApplication.class, args);
	}
}
