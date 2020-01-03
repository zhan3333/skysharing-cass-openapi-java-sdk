package com.skysharing.api.exception;

public class RequestFailedException extends CassApiException {
    public RequestFailedException(String message) {
        super(message);
    }
}
