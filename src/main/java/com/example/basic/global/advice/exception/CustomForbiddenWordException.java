package com.example.basic.global.advice.exception;

public class CustomForbiddenWordException extends RuntimeException {

    public CustomForbiddenWordException() {
    }

    public CustomForbiddenWordException(String message) {
        super(message);
    }

    public CustomForbiddenWordException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
