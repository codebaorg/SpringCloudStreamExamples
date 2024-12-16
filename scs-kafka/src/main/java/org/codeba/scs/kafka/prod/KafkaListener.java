package org.codeba.scs.kafka.prod;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.event.ListenerContainerIdleEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class KafkaListener {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);

    @Bean
    public ApplicationListener<ListenerContainerIdleEvent> idleListener() {
        boolean resume = true;

        return event -> {
            Consumer<?, ?> consumer = event.getConsumer();
            Set<TopicPartition> pausedSet = consumer.paused();

            if (resume && !pausedSet.isEmpty()) {
                Map<String, List<TopicPartition>> topicPartitionGroupedByTopic = pausedSet.stream()
                        .collect(Collectors.groupingBy(TopicPartition::topic, Collectors.toList()));

                topicPartitionGroupedByTopic.forEach((topic, toResumeList) -> {
                    consumer.resume(toResumeList);
                    LOGGER.info("resume Consumer:{} switch to resumed.topic={}", toResumeList, topic);
                });
            }

        };
    }

}
