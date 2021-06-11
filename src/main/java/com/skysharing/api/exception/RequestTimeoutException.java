package com.skysharing.api.exception;

/**
 * 请求超时抛出异常
 *
 * @author zhan
 * @version $Id: $Id
 */
public class RequestTimeoutException extends CassApiException {
    /**
     * <p>Constructor for RequestTimeoutException.</p>
     *
     * @param msg a {@link java.lang.String} object.
     */
    public RequestTimeoutException(String msg) {
        super(msg);
    }
}
