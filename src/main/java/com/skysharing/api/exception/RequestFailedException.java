package com.skysharing.api.exception;

/**
 * 请求错误异常
 */
public class RequestFailedException extends CassApiException {
    public RequestFailedException(String message) {
        super(message);
    }
}
