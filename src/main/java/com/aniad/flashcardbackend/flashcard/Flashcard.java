package com.aniad.flashcardbackend.flashcard;

import com.aniad.flashcardbackend.deck.Deck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
}
