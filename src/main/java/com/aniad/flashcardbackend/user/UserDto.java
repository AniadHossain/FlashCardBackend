package com.aniad.flashcardbackend.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String email;


}
