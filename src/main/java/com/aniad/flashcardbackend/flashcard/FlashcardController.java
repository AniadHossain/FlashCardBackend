package com.aniad.flashcardbackend.flashcard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flashcard")
@RequiredArgsConstructor
public class FlashcardController {
    private final FlashcardService service;

    @PostMapping("/create")
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody FlashcardCreationRequest req){
        return new ResponseEntity<>(service.createFlashcard(req), HttpStatus.OK);
    }
}
