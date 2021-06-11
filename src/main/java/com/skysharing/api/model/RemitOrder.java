package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>RemitOrder class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class RemitOrder {
    @JSONField
    public String orderSN;
    @JSONField
    public String orderUUID;
    @JSONField
    public String orderStatus;
    @JSONField
    public String orderFailStatus;
    @JSONField
    public String requestPayAmount;
    @JSONField
    public String actualPayAmount;
    @JSONField
    public String reachAt;
    @JSONField
    public String orderResponseMsg;

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Order{" +
                "orderSN='" + orderSN + '\'' +
                ", orderUUID='" + orderUUID + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderFailStatus='" + orderFailStatus + '\'' +
                ", requestPayAmount='" + requestPayAmount + '\'' +
                ", actualPayAmount='" + actualPayAmount + '\'' +
                ", reachAt='" + reachAt + '\'' +
                ", orderResponseMsg='" + orderResponseMsg + '\'' +
                '}';
    }
}
