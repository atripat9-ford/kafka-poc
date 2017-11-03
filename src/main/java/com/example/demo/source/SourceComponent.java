package com.example.demo.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.multichannel.TestChannels;

@Component
public class SourceComponent {

	TestChannels channels;
	 
    @Autowired
    public SourceComponent(TestChannels channels) {
        this.channels = channels;
    }

    public void sendMessageToOutputChannel1(String name) {
    	channels.outputChannel1().send(MessageBuilder.withPayload(name).build());
    	channels.outputChannel2().send(MessageBuilder.withPayload(name).build());
    }
}
