package com.aniad.flashcardbackend.user;


import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User,UserDto> {

    @Override
    public UserDto apply(User user) {
        return toUserDto(user);
    }

    public UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .decks(user.getDecks())
                .build();
    }
}
