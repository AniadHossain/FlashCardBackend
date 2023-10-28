package com.aniad.flashcardbackend.auth;

import com.aniad.flashcardbackend.user.AuthenticatedUserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest req, HttpServletResponse res) throws IOException{
        authService.refreshToken(req,res);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticatedUserDto> registerUser(@RequestBody UserRegistrationRequest req){
        return new ResponseEntity<>(authService.registerUser(req), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserDto> loginUser(@RequestBody UserAuthorizationRequest req){
        return new ResponseEntity<>(authService.loginUser(req), HttpStatus.OK);
    }
}
