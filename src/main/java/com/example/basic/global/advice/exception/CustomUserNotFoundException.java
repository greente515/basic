package com.example.basic.global.advice.exception;

public class CustomUserNotFoundException extends RuntimeException {

    public CustomUserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CustomUserNotFoundException(String message) {
        super(message);
    }

    public CustomUserNotFoundException() {
        super();
    }
}
