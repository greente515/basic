package com.example.basic.global.advice.exception;

public class CustomUserExistException extends RuntimeException{
    public CustomUserExistException() {
        super();
    }

    public CustomUserExistException(String message) {
        super(message);
    }

    public CustomUserExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
