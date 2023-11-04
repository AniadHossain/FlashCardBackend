package com.aniad.flashcardbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email){
        return new ResponseEntity<>(service.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }


}
