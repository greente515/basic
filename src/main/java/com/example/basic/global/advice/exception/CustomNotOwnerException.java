package com.example.basic.global.advice.exception;

public class CustomNotOwnerException extends RuntimeException {

    public CustomNotOwnerException() {
        super();
    }

    public CustomNotOwnerException(String message) {
        super(message);
    }

    public CustomNotOwnerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
