package com.example.demo.source;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import com.example.demo.TestChannels;

@SpringBootApplication
@EnableBinding(TestChannels.class)
@Profile("Source")
@ComponentScan(basePackages = {"com.example.demo.source"})
public class SourceApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(SourceApplication.class, args);
//	}
}
