package com.aniad.flashcardbackend.user;

import com.aniad.flashcardbackend.auth.UserAuthorizationRequest;
import com.aniad.flashcardbackend.auth.UserRegistrationRequest;
import com.aniad.flashcardbackend.exception.ExistingUserException;
import com.aniad.flashcardbackend.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    private final UserDtoMapper userDtoMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public UserDto findUserByEmail(String email){
        return repo.findByEmail(email)
                .map(userDtoMapper)
                .orElseThrow(() -> new UserNotFoundException("user with email %s not found".formatted(email)));
    }


    public UserDto createAndSaveUser(UserRegistrationRequest request){
        String firstName = request.firstName();
        String lastName = request.lastName();
        String email = request.email();
        String password = request.password();

        if("".equals(firstName) || "".equals(lastName) || "".equals(email) || "".equals(password)){
            throw new IllegalArgumentException("One or more credentials are missing.");
        }

        if (repo.existsByEmail(email)) {
            throw new ExistingUserException("This user already exists");
        }

        password = encoder.encode(password);

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .decks(new ArrayList<>())
                .build();

        repo.save(user);

        return userDtoMapper.toUserDto(user);
    }

    public boolean isUserAuthorized(UserAuthorizationRequest request){
        String email = request.email();
        String password = request.password();

        User user = repo.findUserByEmail(email);

        return encoder.matches(password, user.getPassword());
    }
}
