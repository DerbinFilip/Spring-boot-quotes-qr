package com.derbin.petclinic.exception;

public class PetNotFoundException extends ApplicationException {
    public PetNotFoundException(String message) {
        super(message);
    }
}
