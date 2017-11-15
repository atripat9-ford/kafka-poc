package com.example.demo.dynamicdestination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("DynamicDestinationApplication")
@ComponentScan(basePackages = {"com.example.demo"})
public class DynamicDestinationApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DynamicDestinationApplication.class, args);
//	}
	
}
