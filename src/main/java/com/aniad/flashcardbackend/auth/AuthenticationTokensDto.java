package com.aniad.flashcardbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationTokensDto {
    private String accessToken;
    private String refreshToken;
    private Date expiresAt;

}
