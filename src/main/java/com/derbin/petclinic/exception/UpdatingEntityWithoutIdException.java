package com.derbin.petclinic.exception;

public class UpdatingEntityWithoutIdException extends NoIdException {
    public UpdatingEntityWithoutIdException(Class<?> classToUpdate) {
        super("Updating " + classToUpdate.getSimpleName() + " without ID parameter!");
    }
}
