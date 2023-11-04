package com.aniad.flashcardbackend.deck;

import com.aniad.flashcardbackend.flashcard.Flashcard;
import com.aniad.flashcardbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//@NamedEntityGraph(name = "Deck.flashcards", attributeNodes = @NamedAttributeNode("flashcards"))
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

    //@OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    //private List<Flashcard> flashcards;
}
