package com.aniad.flashcardbackend.deck;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeckDtoMapper implements Function<Deck, DeckDto> {
    @Override
    public DeckDto apply(Deck deck) {
        return toDeckDto(deck);
    }

    public DeckDto toDeckDto(Deck deck){
        return DeckDto.builder()
                .id(deck.getId())
                .name(deck.getName())
                .build();
    }
}
