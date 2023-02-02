package com.backend.Assure.exception;

public class UserExists extends RuntimeException {

    public UserExists(String message) {
        super(message);
    }
}
