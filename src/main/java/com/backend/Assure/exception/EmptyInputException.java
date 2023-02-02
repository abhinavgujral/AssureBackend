package com.backend.Assure.exception;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException(String message) {
        super(message);
    }
}
