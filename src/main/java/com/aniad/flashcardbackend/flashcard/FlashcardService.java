package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import com.aniad.flashcardbackend.deck.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlashcardService {
    private final FlashcardRepo repo;
    private final DeckService deckService;
    private final FlashcardDtoMapper flashcardDtoMapper;
    public FlashcardDto createFlashcard(FlashcardCreationRequest req) {
        Deck deck = deckService.findDeckById(req.deckId());

        Flashcard flashcard = repo.save(Flashcard.builder()
                .question(req.question())
                .answer(req.answer())
                .deck(deck)
                .build());

        return flashcardDtoMapper.toFlashcardDto(flashcard);
    }

    public List<FlashcardDto> findFlashcardsByDeckId(long id){
        Deck deck = deckService.findDeckById(id);
        return repo.findByDeck(deck).stream()
                .map(flashcardDtoMapper)
                .collect(Collectors.toList());
    }
}
