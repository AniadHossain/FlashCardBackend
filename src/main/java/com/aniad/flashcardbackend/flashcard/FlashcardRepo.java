package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByDeck(Deck deck);

    Optional<Flashcard> findById(long id);
}
