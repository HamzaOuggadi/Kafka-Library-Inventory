package net.hamzaouggadi.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.domain.LibraryEvent;
import net.hamzaouggadi.services.LibraryEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/libraryEvents")
@RequiredArgsConstructor
@Slf4j
public class LibraryEventsController {

    private final LibraryEventService libraryEventService;

    @PostMapping("/postLibraryEvent")
    public ResponseEntity<String> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Before postLibraryEvent.");
        libraryEventService.postLibraryEvent(libraryEvent);
        log.info("After postLibraryEvent.");
        return ResponseEntity.ok("Event Published Successfully");
    }
}
