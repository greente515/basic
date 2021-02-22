package com.example.basic.global.advice.exception;

public class CustomEmailSigninFailedException extends RuntimeException {

    public CustomEmailSigninFailedException() {
        super();
    }

    public CustomEmailSigninFailedException(String message) {
        super(message);
    }

    public CustomEmailSigninFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
