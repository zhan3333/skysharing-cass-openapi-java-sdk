package com.skysharing.api.exception;

/**
 * 请求错误异常
 *
 * @author zhan
 * @version $Id: $Id
 */
public class RequestFailedException extends CassApiException {
    /**
     * <p>Constructor for RequestFailedException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public RequestFailedException(String message) {
        super(message);
    }
}
