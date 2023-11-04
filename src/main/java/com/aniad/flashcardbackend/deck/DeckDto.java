package com.aniad.flashcardbackend.deck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class DeckDto {
    Long id;
    String name;
}
