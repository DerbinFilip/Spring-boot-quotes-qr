package com.derbin.petclinic.exception;

public class UpdatingEntityWithoutIdException extends ApplicationClientException {
    public UpdatingEntityWithoutIdException(Class<?> classToUpdate) {
        super("Updating " + classToUpdate.getSimpleName() + " without ID parameter!");
    }
}
