package com.aniad.flashcardbackend.flashcard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcard")
@RequiredArgsConstructor
public class FlashcardController {
    private final FlashcardService service;

    @PostMapping("/create/{id}")
    public ResponseEntity<FlashcardDto> createFlashcard(@RequestBody FlashcardCreationRequest req, @PathVariable long id){
        return new ResponseEntity<>(service.createFlashcard(req,id), HttpStatus.OK);
    }

    @GetMapping("/flashcards/{id}")
    public  ResponseEntity<List<FlashcardDto>> findFlashcardsByDeck(@PathVariable long id){
        return new ResponseEntity<>(service.findFlashcardsByDeckId(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FlashcardDto> DeleteFlashcardById(@PathVariable long id){
        return new ResponseEntity<>(service.deleteFlashcardById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlashcardDto> UpdateFlashcardById(@PathVariable long id, @RequestBody FlashcardUpdateRequest request){
        return new ResponseEntity<>(service.updateFlashcardById(id,request),HttpStatus.OK);
    }
}
