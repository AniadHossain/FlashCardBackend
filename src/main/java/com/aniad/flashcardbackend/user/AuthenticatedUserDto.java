package com.aniad.flashcardbackend.user;

import com.aniad.flashcardbackend.auth.AuthenticationTokensDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AuthenticatedUserDto {
    private UserDto user;
    private AuthenticationTokensDto tokens;
}
