package net.hamzaouggadi.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.entities.LibraryEvent;
import net.hamzaouggadi.enums.EventType;
import net.hamzaouggadi.repositories.LibraryEventRepository;
import net.hamzaouggadi.services.LibraryEventService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LibraryEventServiceImpl implements LibraryEventService {

    private final ObjectMapper objectMapper;
    private final LibraryEventRepository libraryEventRepository;

    @Override
    public void processEvent(ConsumerRecord<Long, String> consumerRecord) throws Exception {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("Event : {} ", libraryEvent);

        switch (libraryEvent.getEventType()) {
            case NEW -> save(libraryEvent);
            case UPDATE -> {
                validate(libraryEvent);
                save(libraryEvent);
            }
            default -> log.info("Invalid Library Event !");
        }
    }

    private void validate(LibraryEvent libraryEvent) throws Exception {
        Optional<LibraryEvent> event = libraryEventRepository.findById(libraryEvent.getEventId());
        if (event.isEmpty()) {
            throw new Exception("Event not found in DB !");
        }
        log.info("Validation is Successful.");
    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventRepository.save(libraryEvent);
        log.info("Successfully saved Event");
    }


}
