package net.hamzaouggadi.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.domain.LibraryEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@RequiredArgsConstructor
@Slf4j
public class LibraryEventProducer {

    private final KafkaTemplate<Long, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private static final String TOPIC = "Library_Events";


    public void sendLibraryEventAsync(LibraryEvent libraryEvent) throws JsonProcessingException {
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

    public void sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(libraryEvent);
        Long key = libraryEvent.getEventId();

        try {
            SendResult<Long, String> result = kafkaTemplate.sendDefault(key, value).get(10, TimeUnit.SECONDS);
            handleSuccess(key, value, result);
        } catch (InterruptedException e) {
            handleFailure(key, value, e);
        } catch (TimeoutException | ExecutionException e) {
            handleFailure(key, value, e.getCause());
        }
    }

    public void sendEventAsync_Approach2(LibraryEvent libraryEvent) throws JsonProcessingException {
        Long key = libraryEvent.getEventId();
        String value = objectMapper.writeValueAsString(libraryEvent);
        ProducerRecord<Long, String> record = producerRecordBuilder(TOPIC, key, value);

        CompletableFuture<SendResult<Long, String>> completableFuture = kafkaTemplate.send(record);

        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(key, value, result);
            } else {
                handleFailure(key, value, ex);
            }
        });
    }

    private ProducerRecord<Long, String> producerRecordBuilder(String topic, Long key, String value) {
        return new ProducerRecord<>(topic, key, value);
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

    private void handleSuccess(Long key, String value) {
        log.info("Message Sent Successfully for the Key {}, Value {}.", key, value);
    }
}
