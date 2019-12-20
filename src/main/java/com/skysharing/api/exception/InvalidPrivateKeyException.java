package com.skysharing.api.exception;

public class InvalidPrivateKeyException extends CassApiException {
    public InvalidPrivateKeyException(String message) {
        super(message);
    }
}
