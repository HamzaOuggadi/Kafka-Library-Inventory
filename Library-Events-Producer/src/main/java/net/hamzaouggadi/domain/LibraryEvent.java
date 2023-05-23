package net.hamzaouggadi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.hamzaouggadi.entities.Book;
import net.hamzaouggadi.enums.EventType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryEvent {
    private Long eventId;
    private EventType eventType;
    private Book book;
}
