package com.aniad.flashcardbackend.auth;

public record UserRegistrationRequest(String firstName,String lastName, String email, String password) {
}
