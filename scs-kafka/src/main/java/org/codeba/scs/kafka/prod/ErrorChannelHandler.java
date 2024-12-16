package org.codeba.scs.kafka.prod;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.kafka.support.KafkaSendFailureException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * The type Error channel handler.
 *
 * @author codeba
 */
@Component
public class ErrorChannelHandler {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorChannelHandler.class);

    /**
     * Errors.
     *
     * @param error the error
     */
    @StreamListener("errorChannel")
    public void errors(Message<?> error) {
        final Object payload = error.getPayload();
        LOGGER.info("errorChannel: {}", payload);

        if (payload instanceof KafkaSendFailureException) {
            KafkaSendFailureException failure = (KafkaSendFailureException) payload;
            final ProducerRecord<?, ?> record = failure.getRecord();
            final Object value = record.value();
            LOGGER.info("errorChannel value: {}", new String((byte[]) value));
        }
        // do yourself things，the general treatment is to persist the message that failed to be sent for subsequent re-sending
    }

}
