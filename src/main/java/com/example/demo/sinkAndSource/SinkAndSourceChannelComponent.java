package com.example.demo.sinkAndSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(SinkAndSourceChannels.class)
public class SinkAndSourceChannelComponent {

	private SinkAndSourceChannels source;

    @Autowired
    public SinkAndSourceChannelComponent(SinkAndSourceChannels source) {
        this.source = source;
    }

    public void sendMessageToOutputChannel1(String name) {
         source.outputChannel1().send(MessageBuilder.withPayload(name).build());
         source.outputChannel2().send(MessageBuilder.withPayload(name).build());
    }
    
    @StreamListener(SinkAndSourceChannels.INPUT_CHANNEL_1)
    public void listenToChannel1(String message) {
    	System.out.println("listenToChannel1 got message "+message);
    }
}
