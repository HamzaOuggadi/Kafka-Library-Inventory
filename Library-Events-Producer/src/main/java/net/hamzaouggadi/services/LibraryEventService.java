package net.hamzaouggadi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.hamzaouggadi.domain.LibraryEvent;

public interface LibraryEventService {
    void postLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException;
}
