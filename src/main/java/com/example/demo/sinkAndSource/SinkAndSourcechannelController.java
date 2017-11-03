package com.example.demo.sinkAndSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SinkAndSourcechannelController {
	
	private SinkAndSourceChannelComponent channelComponent;
	
	@Autowired
	public SinkAndSourcechannelController(SinkAndSourceChannelComponent channel) {
		this.channelComponent = channel;
	}
	
	@RequestMapping(path = "/{message}", method = RequestMethod.POST, consumes = "*/*")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void handleRequest(@RequestBody String body, @PathVariable("message") String message,  @RequestHeader("content-type") Object contentType) {
		channelComponent.sendMessageToOutputChannel1(message);
	}

}
