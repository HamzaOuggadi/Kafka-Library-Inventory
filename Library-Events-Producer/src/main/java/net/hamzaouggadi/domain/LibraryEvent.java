package net.hamzaouggadi.domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    private Book book;
}
