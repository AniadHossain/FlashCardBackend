package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import com.aniad.flashcardbackend.deck.DeckService;
import com.aniad.flashcardbackend.exception.UserNotFoundException;
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

    public FlashcardDto deleteFlashcardById(long id) {
        Flashcard flashcard = findFlashcardById(id);

        repo.delete(flashcard);
        return flashcardDtoMapper.toFlashcardDto(flashcard);
    }

    private Flashcard findFlashcardById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("flashcard with id %s not found".formatted(id)));
    }

    public FlashcardDto updateFlashcardById(long id, FlashcardUpdateRequest request) {
        Flashcard flashcard = findFlashcardById(id);
        flashcard.setQuestion(request.question());
        flashcard.setAnswer(request.answer());
        return flashcardDtoMapper.toFlashcardDto(repo.save(flashcard));
    }
}
