package com.aniad.flashcardbackend.exception;

public class ExistingUserException extends RuntimeException{
    public ExistingUserException(String message){super(message);}
}
