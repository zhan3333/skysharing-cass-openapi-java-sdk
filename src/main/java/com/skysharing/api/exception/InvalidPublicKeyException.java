package com.skysharing.api.exception;

/**
 * <p>InvalidPublicKeyException class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class InvalidPublicKeyException extends CassApiException {
    /**
     * <p>Constructor for InvalidPublicKeyException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public InvalidPublicKeyException(String message) {
        super(message);
    }
}
