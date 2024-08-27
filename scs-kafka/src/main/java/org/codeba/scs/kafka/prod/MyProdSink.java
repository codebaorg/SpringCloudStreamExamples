package org.codeba.scs.kafka.prod;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * The interface My sink.
 *
 * @author codeba
 */
public interface MyProdSink {
    /**
     * The constant INPUT.
     */
    String INPUT = "my-prod-input";

    /**
     * Input subscribable channel.
     *
     * @return the subscribable channel
     */
    @Input(INPUT)
    SubscribableChannel input();

}
