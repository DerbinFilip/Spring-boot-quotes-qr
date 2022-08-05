package com.derbin.petclinic.exception;

public class NoIdException extends RuntimeException {
    public NoIdException(final String message) {
        super(message);
    }
}
