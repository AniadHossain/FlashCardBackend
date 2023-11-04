package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import com.aniad.flashcardbackend.deck.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepo repo;
    private final DeckService deckService;
    public Flashcard createFlashcard(FlashcardCreationRequest req) {
        Deck deck = deckService.findDeckById(req.deckId());

        return Flashcard.builder()
                .question(req.question())
                .answer(req.answer())
                //.deck(deck)
                .build();

    }
}
