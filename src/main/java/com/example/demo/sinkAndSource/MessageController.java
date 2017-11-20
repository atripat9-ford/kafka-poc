package com.example.demo.sinkAndSource;

import java.time.Instant;

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
	public String sendMessage(@RequestParam("inputParam") String userGUID) throws JsonProcessingException {
		System.out.println("Received request to send message "+userGUID);
		
		BaseFPEvent baseFPEvent = new BaseFPEvent("ServiceDue","1234",userGUID,"9BFZH55L0J8457996","E4281A6E-34C3-11E7-B6EC-000D3A196736","MSL-APPID",Instant.now().toString() );
		mslMessageSender.sendMessage(baseFPEvent, KafkaUtils.MIME_TYPE);
		//mslMessageSender.sendCar(car, KafkaUtils.MIME_TYPE);
	    //mslJSONMessageSender.sendCar(car, KafkaUtils.MIME_TYPE);
		
		return "Sent userGuid as "+userGUID;
	}
}
