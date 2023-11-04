package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import com.aniad.flashcardbackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByDeck(Deck deck);
}
