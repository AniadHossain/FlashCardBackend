package com.aniad.flashcardbackend.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @EntityGraph(value = "User.decks")
    Optional<User> findByEmail(String email);

    @EntityGraph(value = "User.decks")
    Optional<User> findById(long id);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
