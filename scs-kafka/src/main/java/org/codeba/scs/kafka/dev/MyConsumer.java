package org.codeba.scs.kafka.dev;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * The type My consumer.
 *
 * @author codeba
 */
@Component
@EnableBinding(MySink.class)
public class MyConsumer {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyConsumer.class);

    /**
     * Consume.
     *
     * @param payload the payload
     */
    @StreamListener(MySink.INPUT)
    public void consume(
            @Payload Object payload
    ) {
        LOGGER.info("payload:{}", payload.toString());
        // do something
    }

}
