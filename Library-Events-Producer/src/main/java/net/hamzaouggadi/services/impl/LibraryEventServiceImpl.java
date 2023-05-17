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
    public void postLibraryEventAsync(LibraryEvent libraryEvent) throws JsonProcessingException {
        libraryEventProducer.sendLibraryEventAsync(libraryEvent);
    }

    @Override
    public void postLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {
        libraryEventProducer.sendLibraryEventSync(libraryEvent);
    }

    @Override
    public void sendEventAsync_Approach2(LibraryEvent libraryEvent) throws JsonProcessingException {
        libraryEventProducer.sendEventAsync_Approach2(libraryEvent);
    }
}
