package com.aniad.flashcardbackend.deck;

import com.aniad.flashcardbackend.flashcard.Flashcard;
import com.aniad.flashcardbackend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Deck.flashcards", attributeNodes = @NamedAttributeNode("flashcards"))
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Flashcard> flashcards = new ArrayList<>();
}
