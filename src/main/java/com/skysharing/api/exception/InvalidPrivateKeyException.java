package com.skysharing.api.exception;

/**
 * <p>InvalidPrivateKeyException class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class InvalidPrivateKeyException extends CassApiException {
    /**
     * <p>Constructor for InvalidPrivateKeyException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public InvalidPrivateKeyException(String message) {
        super(message);
    }
}
