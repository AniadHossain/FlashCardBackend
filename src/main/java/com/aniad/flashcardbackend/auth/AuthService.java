package com.aniad.flashcardbackend.auth;

import com.aniad.flashcardbackend.config.UserAuthProvider;
import com.aniad.flashcardbackend.exception.UnauthorisedException;
import com.aniad.flashcardbackend.user.AuthenticatedUserDto;
import com.aniad.flashcardbackend.user.UserDto;
import com.aniad.flashcardbackend.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;


    public void refreshToken(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null) {
            String[] sections = header.split(" ");

            if (sections.length == 2 && "Bearer".equals(sections[0]) && userAuthProvider.validateToken(sections[1]).isAuthenticated()) {
                String email = userAuthProvider.extractEmail(sections[1]);
                String accessToken = userAuthProvider.createAccessToken(email);
                String refreshToken = userAuthProvider.createRefreshToken(email);
                AuthenticationTokensDto authResponse = AuthenticationTokensDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .expiresAt(userAuthProvider.extractExpiration(accessToken))
                        .build();
                new ObjectMapper().writeValue(res.getOutputStream(), authResponse);
            }
        }

    }

    public AuthenticatedUserDto registerUser(UserRegistrationRequest request){
        UserDto userDto = userService.createAndSaveUser(request);

        return AuthenticatedUserDto.builder()
                .user(userDto)
                .tokens(createTokens(userDto))
                .build();

    }

    public AuthenticatedUserDto loginUser(UserAuthorizationRequest request){
        String email = request.email();

        UserDto userDto = userService.findUserByEmail(email);

        if(userService.isUserAuthorized(request)){
            return AuthenticatedUserDto.builder()
                    .user(userDto)
                    .tokens(createTokens(userDto))
                    .build();
        }

        throw new UnauthorisedException("User is not authorized");

    }

    private AuthenticationTokensDto createTokens(UserDto userDto){
        String accessToken =  userAuthProvider.createAccessToken(userDto.getEmail());
        String refreshToken =  userAuthProvider.createRefreshToken(userDto.getEmail());
        return new AuthenticationTokensDto(accessToken,
                refreshToken,userAuthProvider.extractExpiration(accessToken));
    }


}
