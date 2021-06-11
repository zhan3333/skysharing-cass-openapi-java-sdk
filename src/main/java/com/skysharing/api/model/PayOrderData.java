package com.skysharing.api.model;

/**
 * <p>PayOrderData class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayOrderData {
    public String name;
    public String description;

    /**
     * <p>Constructor for PayOrderData.</p>
     */
    public PayOrderData() {
    }

    /**
     * <p>Constructor for PayOrderData.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param description a {@link java.lang.String} object.
     */
    public PayOrderData(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
