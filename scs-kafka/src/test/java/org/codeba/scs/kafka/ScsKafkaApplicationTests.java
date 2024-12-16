package org.codeba.scs.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

@SpringBootTest
class ScsKafkaApplicationTests {

    @Autowired
    private BinderAwareChannelResolver channelResolver;

    @Test
    void devTest() {
        // send message
        String topic = "test-topic";
        final MyMessage myMessage = new MyMessage("hello world", 2024);
        final Message<MyMessage> message = MessageBuilder.withPayload(myMessage).build();
        channelResolver.resolveDestination(topic).send(message);
    }

    @Test
    void prodTest() {
        // send message
        for (int i = 0; i < 100; i++) {
            String topic = "test-prod" + i + "-topic";
            final MyMessage myMessage = new MyMessage("hello world", 2024);
            final Message<MyMessage> message = MessageBuilder.withPayload(myMessage).build();
            channelResolver.resolveDestination(topic).send(message);
        }
    }

}
