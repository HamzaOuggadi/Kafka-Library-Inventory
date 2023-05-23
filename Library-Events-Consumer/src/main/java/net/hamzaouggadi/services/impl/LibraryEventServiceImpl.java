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

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LibraryEventServiceImpl implements LibraryEventService {

    private final ObjectMapper objectMapper;
    private final LibraryEventRepository libraryEventRepository;

    @Override
    public void processEvent(ConsumerRecord<Long, String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("Event : {} ", libraryEvent);

        switch (libraryEvent.getEventType()) {
            case NEW -> save(libraryEvent);
            case UPDATE -> update(libraryEvent);
            default -> log.info("Invalid Library Event !");
        }
    }

    private void update(LibraryEvent libraryEvent) {

    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventRepository.save(libraryEvent);
        log.info("Successfully saved Event");
    }


}
