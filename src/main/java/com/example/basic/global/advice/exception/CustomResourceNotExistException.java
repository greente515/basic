package com.example.basic.global.advice.exception;

public class CustomResourceNotExistException extends RuntimeException {

    public CustomResourceNotExistException() {
        super();
    }

    public CustomResourceNotExistException(String message) {
        super(message);
    }

    public CustomResourceNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
