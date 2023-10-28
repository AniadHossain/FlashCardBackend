package com.aniad.flashcardbackend.auth;

public record UserAuthorizationRequest(String email, String password) {
}
