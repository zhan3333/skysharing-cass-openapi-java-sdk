package com.skysharing.api.exception;

public class ResponseNotValidException extends CassApiException {
    public ResponseNotValidException(String message) {
        super(message);
    }
}
