package com.skysharing.api.notify;

public class DefaultNotify {

    public String body;

    public String resourceID;

    public Number pushType;

    public String notifyUrl;

    public String status;

    public static DefaultNotify createFromBody(String string) {
        return new DefaultNotify();
    }
}
