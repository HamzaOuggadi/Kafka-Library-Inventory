package net.hamzaouggadi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.hamzaouggadi.domain.LibraryEvent;

public interface LibraryEventService {
    void postLibraryEventAsync(LibraryEvent libraryEvent) throws JsonProcessingException;
    void postLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException;
    void sendEventAsync_Approach2(LibraryEvent libraryEvent) throws JsonProcessingException;
}
