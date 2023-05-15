package net.hamzaouggadi.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.domain.LibraryEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryEventProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;

    private final ObjectMapper objectMapper;


    public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(libraryEvent);
        Long key = libraryEvent.getEventId();
        CompletableFuture<SendResult<Long, String>> completableFuture = kafkaTemplate.sendDefault(key, value);

        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(key, value, result);
            } else {
                handleFailure(key, value, ex);
            }
        });
    }

    private void handleFailure(Long key, String value, Throwable ex) {
        log.error("Failed to Send the Message with Key {}, Value {}, with Exception {}", key, value, ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error OnFailure");
        }
    }

    private void handleSuccess(Long key, String value, SendResult<Long, String> result) {
        log.info("Message Sent Successfully for the Key {}, Value {}, partition is {}.", key, value, result.getRecordMetadata().partition());
    }
}
