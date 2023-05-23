package net.hamzaouggadi.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.services.LibraryEventService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryEventConsumer {

    private final LibraryEventService libraryEventService;
    @KafkaListener(topics = {"Library_Events"})
    public void onMessage(ConsumerRecord<Long, String> record) throws Exception {
        log.info("Consumer Record : " + record);
        libraryEventService.processEvent(record);
    }
}
