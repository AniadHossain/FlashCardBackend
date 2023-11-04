package com.aniad.flashcardbackend.deck;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deck")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;

    @PostMapping("/create")
    public ResponseEntity<DeckDto> createDeck(@RequestBody DeckCreationRequest req){
        return new ResponseEntity<>(deckService.createDeck(req), HttpStatus.OK);
    }

    @GetMapping("/decks/{id}")
    public ResponseEntity<List<DeckDto>> getDecksByUserId(@PathVariable long id){
        return new ResponseEntity<>(deckService.findDecksByUserId(id), HttpStatus.OK);
    }

}
