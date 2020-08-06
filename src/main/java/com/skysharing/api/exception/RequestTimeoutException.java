package com.skysharing.api.exception;

/**
 * 请求超时抛出异常
 */
public class RequestTimeoutException extends CassApiException {
    public RequestTimeoutException(String msg) {
        super(msg);
    }
}
