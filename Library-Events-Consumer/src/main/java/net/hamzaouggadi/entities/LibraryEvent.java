package net.hamzaouggadi.entities;

import jakarta.persistence.*;
import lombok.*;
import net.hamzaouggadi.enums.EventType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryEvent {
    @Id
    private Long eventId;
    @Enumerated(value = EnumType.STRING)
    private EventType eventType;
    @OneToOne(mappedBy = "libraryEvent", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Book book;
}
