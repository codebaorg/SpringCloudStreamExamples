package org.codeba.scs.kafka.prod;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
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
        LOGGER.info("errorChannel: {}", new String((byte[]) payload));
        // do yourself things
    }

}
