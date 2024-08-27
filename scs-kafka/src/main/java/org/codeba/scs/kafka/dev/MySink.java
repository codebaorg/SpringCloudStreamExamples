package org.codeba.scs.kafka.dev;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * The interface My sink.
 *
 * @author codeba
 */
public interface MySink {
    /**
     * The constant INPUT.
     */
    String INPUT = "my-input";

    /**
     * Input subscribable channel.
     *
     * @return the subscribable channel
     */
    @Input(INPUT)
    SubscribableChannel input();

}
