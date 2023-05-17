package net.hamzaouggadi.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hamzaouggadi.domain.LibraryEvent;
import net.hamzaouggadi.services.LibraryEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libraryEvents")
@RequiredArgsConstructor
@Slf4j
public class LibraryEventsController {

    private final LibraryEventService libraryEventService;

    @PostMapping("/postEventAsync")
    public ResponseEntity<String> postLibraryEventAsync(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Before postLibraryEvent Async.");
        libraryEventService.postLibraryEventAsync(libraryEvent);
        log.info("After postLibraryEvent Async.");
        return ResponseEntity.ok("Event Successfully Published Asynchronously.");
    }

    @PostMapping("/postEventSync")
    public ResponseEntity<String> postLibraryEventSync(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        log.info("Before postLibraryEvent Sync.");
        libraryEventService.postLibraryEventSync(libraryEvent);
        log.info("After postLibraryEvent Sync.");
        return ResponseEntity.ok("Event Successfully published Synchronously.");
    }


    @PostMapping("/postEventApproach2")
    public ResponseEntity<String> postLibraryEventAsync_Approach2(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        libraryEventService.sendEventAsync_Approach2(libraryEvent);
        return ResponseEntity.ok("Event Successfully Published Asynchronously.");
    }


    @PutMapping("/putEvent")
    public ResponseEntity<String> putLibraryEventAsync(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {
        if (libraryEvent.getEventId()!=null) {
            libraryEventService.postLibraryEventAsync(libraryEvent);
            return ResponseEntity.ok("Event Successfully Published Asynchronously.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide an EventID");
        }
    }
}
