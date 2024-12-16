package org.codeba.scs.kafka.prod;

import org.codeba.scs.kafka.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Test controller.
 *
 */
@RestController
public class TestController {

    @Autowired
    private BinderAwareChannelResolver channelResolver;

    @RequestMapping("/send")
    public void sendExceptionTest() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            String topic = "foo";
            final MyMessage myMessage = new MyMessage("bar", 2024);
            final Message<MyMessage> message = MessageBuilder.withPayload(myMessage).build();
            channelResolver.resolveDestination(topic).send(message);
            Thread.sleep(1000);
        }
    }

}
