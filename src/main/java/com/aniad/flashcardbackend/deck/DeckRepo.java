package com.aniad.flashcardbackend.deck;

import com.aniad.flashcardbackend.user.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepo extends JpaRepository<Deck,Long> {

    @EntityGraph(value = "Deck.flashcards")
    Optional<Deck> findById(long id);

    @EntityGraph(value = "Deck.flashcards")
    List<Deck> findByUser(User user);

}
