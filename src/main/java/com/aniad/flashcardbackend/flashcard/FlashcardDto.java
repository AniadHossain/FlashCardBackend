package com.aniad.flashcardbackend.flashcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class FlashcardDto {
    Long id;
    String question;
    String answer;
}
