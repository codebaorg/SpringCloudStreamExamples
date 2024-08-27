package org.codeba.scs.kafka.prod;


import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type My consumer.
 *
 * @author codeba
 */
@Component
@EnableBinding(MyProdSink.class)
public class MyProdConsumer {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyProdConsumer.class);

    private AtomicInteger counter = new AtomicInteger(0);

    /**
     * Consume.
     *
     * @param payloads     the payloads
     * @param topics       the topics
     * @param partitionIds the partition ids
     * @param groupId      the group id
     * @param consumer     the consumer
     */
    @StreamListener(MyProdSink.INPUT)
    public void consume(
            @Payload List<Object> payloads,
            @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitionIds,
            @Header(KafkaHeaders.GROUP_ID) String groupId,
            @Header(KafkaHeaders.CONSUMER) Consumer<?, ?> consumer,
            @Header(KafkaHeaders.ACKNOWLEDGMENT) Acknowledgment acknowledgment
    ) {
        for (int i = 0; i < payloads.size(); i++) {
            byte[] bytes = (byte[]) payloads.get(i);
            LOGGER.info("payload:{} from topic:{}, partitionId:{}, groupId:{}", new String(bytes), topics.get(i), partitionIds.get(i), groupId);
        }

        // ack
        acknowledgment.acknowledge();

        LOGGER.info("consumer message total:{}", counter.addAndGet(payloads.size()));

    }

}
