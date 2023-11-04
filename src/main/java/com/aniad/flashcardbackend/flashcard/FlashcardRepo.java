package com.aniad.flashcardbackend.flashcard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {
}
