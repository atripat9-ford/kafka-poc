package com.example.demo.sinkAndSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.converter.CompositeMessageConverterFactory;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

import com.example.demo.util.KafkaUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableBinding(SinkAndSourceChannels.class)
@ComponentScan(basePackages = {"com.example.demo.sinkAndSource","com.example.demo.generated","com.example.demo.util"})
@Configuration
public class SinkAndSourceChannelApplication{

    
	@Bean
	public MessageConverter carMessageConverter() throws IOException {
	      AvroSchemaMessageConverter converter =  new AvroSchemaMessageConverter(MimeType.valueOf(KafkaUtils.MIME_TYPE));
	     // converter.setSchemaLocation(new ClassPathResource("avro/AvroCar.avsc"));
	      converter.setSchemaLocation(new ClassPathResource("avro/FPEvent.avsc"));
		  return converter;
	}
	
	@Bean
	public CompositeMessageConverterFactory compositeMessageConverterFactory(MessageConverter carMessageConverter, ObjectMapper objectMapper) {
		List<MessageConverter> converters = new ArrayList<>();
		converters.add(carMessageConverter);
		CompositeMessageConverterFactory factory = new CompositeMessageConverterFactory(converters, objectMapper);
		
		return factory;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SinkAndSourceChannelApplication.class, args);
	}
}
