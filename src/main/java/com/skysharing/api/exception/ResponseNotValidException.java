package com.skysharing.api.exception;

/**
 * <p>ResponseNotValidException class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class ResponseNotValidException extends CassApiException {
    /**
     * <p>Constructor for ResponseNotValidException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public ResponseNotValidException(String message) {
        super(message);
    }
}
