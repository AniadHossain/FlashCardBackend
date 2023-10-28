package com.aniad.flashcardbackend.config;

import com.aniad.flashcardbackend.user.UserDto;
import com.aniad.flashcardbackend.user.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-value}")
    private  String secretKey;
    private final UserService userService;

    @PostConstruct
    protected void init(){secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());}

    public String createAccessToken(String login){

        return buildToken(login,10 * 1000);
    }

    public String createRefreshToken(String login){

        return buildToken(login,10800000);
    }

    private String buildToken(String login,long validity) {
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + validity))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token){
        UserDto user = userService.findUserByEmail(extractEmail(token));

        return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }

    public String extractEmail(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decoded =  verifier.verify(token);
        return decoded.getIssuer();
    }

    public Date extractExpiration(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decoded =  verifier.verify(token);
        return decoded.getExpiresAt();
    }
}
