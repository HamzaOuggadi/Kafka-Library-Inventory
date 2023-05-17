package net.hamzaouggadi.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventConsumer {

    @KafkaListener(topics = {"Library_Events"})
    public void onMessage(ConsumerRecord<Long, String> record) {
        log.info("Consumer Record : " + record);
    }
}
