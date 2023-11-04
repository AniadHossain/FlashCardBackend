package com.aniad.flashcardbackend.deck;

import com.aniad.flashcardbackend.user.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepo extends JpaRepository<Deck,Long> {

    //@EntityGraph(value = "Deck.flashcards")
    Deck findById(long id);

    List<Deck> findByUser(User user);

//    Deck findDecksByUserId(User user);
}
