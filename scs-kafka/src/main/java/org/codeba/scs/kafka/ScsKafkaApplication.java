package org.codeba.scs.kafka;

import org.codeba.scs.kafka.dev.MySink;
import org.codeba.scs.kafka.prod.MyProdSink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
public class ScsKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScsKafkaApplication.class, args);
    }

}
