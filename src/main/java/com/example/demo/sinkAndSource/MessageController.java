package com.example.demo.sinkAndSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.KafkaUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class MessageController {
	
	MSLMessageSender mslMessageSender;
	MSLJsonMessageSender mslJSONMessageSender;
	
	@Autowired
	public MessageController(MSLMessageSender mslMessageSender, MSLJsonMessageSender mslJSONMessageSender) {
		this.mslMessageSender = mslMessageSender;
		this.mslJSONMessageSender = mslJSONMessageSender;
	}
	
	@RequestMapping("/sendMessage")
	public String sendMessage(@RequestParam("engine") String engine) throws JsonProcessingException {
		System.out.println("Received request to send message "+engine);
		
		Car car = new Car("2017", "ford", "mustang", engine);
	    mslMessageSender.sendCar(car, KafkaUtils.MIME_TYPE);
	    //mslJSONMessageSender.sendCar(car, KafkaUtils.MIME_TYPE);
		
		return "Send engine as "+engine;
	}
}
