package com.backend.Assure.exception;

public class IncorrectPasswordAndEmail extends RuntimeException {

    public IncorrectPasswordAndEmail(String msg) {
        super(msg);
    }

}
