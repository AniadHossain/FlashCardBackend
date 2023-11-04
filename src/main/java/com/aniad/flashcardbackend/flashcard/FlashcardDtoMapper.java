package com.aniad.flashcardbackend.flashcard;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FlashcardDtoMapper implements Function<Flashcard,FlashcardDto> {
    @Override
    public FlashcardDto apply(Flashcard flashcard) {
        return toFlashcardDto(flashcard);
    }

    public FlashcardDto toFlashcardDto(Flashcard flashcard){
        return FlashcardDto.builder()
                .id(flashcard.getId())
                .question(flashcard.getQuestion())
                .answer(flashcard.getAnswer())
                .build();
    }
}
