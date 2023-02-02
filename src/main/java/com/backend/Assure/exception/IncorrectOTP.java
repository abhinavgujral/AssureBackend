package com.backend.Assure.exception;

public class IncorrectOTP extends RuntimeException {

    public IncorrectOTP(String message) {
        super(message);
    }
}
