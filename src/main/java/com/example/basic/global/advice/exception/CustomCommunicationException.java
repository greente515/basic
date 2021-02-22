package com.example.basic.global.advice.exception;

public class CustomCommunicationException extends RuntimeException {

    public CustomCommunicationException() {
        super();
    }

    public CustomCommunicationException(String message) {
        super(message);
    }

    public CustomCommunicationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
