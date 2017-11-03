package com.example.demo.multichannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TestChannels.class)
public class TestChannelComponent {

	private TestChannels source;

    @Autowired
    public TestChannelComponent(TestChannels source) {
        this.source = source;
    }

    public void sendMessageToOutputChannel1(String name) {
         source.outputChannel1().send(MessageBuilder.withPayload(name).build());
         source.outputChannel2().send(MessageBuilder.withPayload(name).build());
    }
}
