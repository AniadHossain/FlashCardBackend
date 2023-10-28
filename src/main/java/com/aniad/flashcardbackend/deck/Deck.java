package com.aniad.flashcardbackend.deck;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Deck {
    @Id
    long id;
}
