package net.hamzaouggadi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface LibraryEventService {
    void processEvent(ConsumerRecord<Long, String> consumerRecord) throws Exception;

}
