package com.derbin.petclinic.exception.handler;

public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
    public ErrorResponse(Throwable throwable){
        this.message = throwable.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
