package net.hamzaouggadi.services;

import net.hamzaouggadi.domain.LibraryEvent;

public interface LibraryEventService {
    void postLibraryEvent(LibraryEvent libraryEvent);
}
