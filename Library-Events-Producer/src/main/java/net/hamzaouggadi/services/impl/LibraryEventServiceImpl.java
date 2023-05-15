package net.hamzaouggadi.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.domain.LibraryEvent;
import net.hamzaouggadi.producer.LibraryEventProducer;
import net.hamzaouggadi.services.LibraryEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class LibraryEventServiceImpl implements LibraryEventService {

    private final LibraryEventProducer libraryEventProducer;

    @Override
    public void postLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        libraryEventProducer.sendLibraryEvent(libraryEvent);
    }
}
