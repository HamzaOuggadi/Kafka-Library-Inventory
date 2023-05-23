package net.hamzaouggadi.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/*
@Component
*/
@Slf4j
public class LibraryEventConsumerManualOffset implements AcknowledgingMessageListener<Long, String> {

    @Override
    @KafkaListener(topics = {"Library_Events"})
    public void onMessage(ConsumerRecord<Long, String> consumerRecord, Acknowledgment acknowledgment) {
        log.info("ConsumerRecord on Manual Offset : {} ", consumerRecord);
        assert acknowledgment != null : "Acknowledgement is Null.";
        acknowledgment.acknowledge();
    }
}
