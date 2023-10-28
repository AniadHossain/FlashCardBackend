package com.aniad.flashcardbackend.user;

import com.aniad.flashcardbackend.deck.Deck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="app_user")
@NamedEntityGraph(name = "User.decks", attributeNodes = @NamedAttributeNode("decks"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany
    private List<Deck> decks;
}
