package com.skysharing.api.exception;

public class InvalidPublicKeyException extends CassApiException {
    public InvalidPublicKeyException(String message) {
        super(message);
    }
}
