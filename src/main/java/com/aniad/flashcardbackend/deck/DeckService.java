package com.aniad.flashcardbackend.deck;

import com.aniad.flashcardbackend.exception.UserNotFoundException;
import com.aniad.flashcardbackend.user.User;
import com.aniad.flashcardbackend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckRepo repo;
    private final UserService userService;
    private final DeckDtoMapper deckDtoMapper;
    public DeckDto createDeck(DeckCreationRequest req) {
        String name = req.name();
        long id = req.userId();

        User user = userService.findUserById(id);



        Deck deck = repo.save(Deck.builder()
                .name(name)
                .user(user)
                .build());

        return deckDtoMapper.toDeckDto(deck);
    }

    public Deck findDeckById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("deck with id %s not found".formatted(id)));
    }

    public List<DeckDto> findDecksByUserId(long id){
        User user = userService.findUserById(id);
        return repo.findByUser(user).stream()
                .map(deckDtoMapper)
                .collect(Collectors.toList());
    }

    public DeckDto deleteDeckById(long id) {
        Optional<Deck> deck = repo.findById(id);
        if(deck.isEmpty()){
            throw new UserNotFoundException("deck with id %s not found".formatted(id));
        }
        repo.delete(deck.get());
        return deckDtoMapper.toDeckDto(deck.get());
    }
}
