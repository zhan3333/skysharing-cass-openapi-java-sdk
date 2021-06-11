package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayAliRemitResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付宝批量付款
 *
 * @see PayAliRemitResponse
 * @author zhan
 * @version $Id: $Id
 */
public class PayAliRemitRequest extends CassPayRequest<PayAliRemitResponse> {

    /**
     *
     */
    public String method = "Vzhuo.AliRemit.Pay";

    /** {@inheritDoc} */
    @Override
    public PayAliRemitResponse makeResponse(JSONObject response) {
        return new PayAliRemitResponse(response);
    }

    /**
     * 设置付款通道
     *
     * @param payChannelK 付款通道
     * @return this
     * @see com.skysharing.api.Constants
     */
    public PayAliRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    /**
     * 设置收款通道
     *
     * @param k 收款通道 key
     * @return this
     * @see com.skysharing.api.Constants
     * @since 2.3.5
     */
    public PayAliRemitRequest setPayeeChannelType(String k) {
        this.bizMap.put("payeeChannelType", k);
        return this;
    }

    /**
     * 设置多个订单
     *
     * @param orders 订单列表
     * @return this
     */
    public PayAliRemitRequest setOrders(List<AliPayOrder> orders) {
        this.bizMap.put("orderData", orders);
        return this;
    }

    /**
     * 设置订单对象
     *
     * @param orders 银行卡收款订单列表
     * @return this
     * @since 2.3.5
     */
    public PayAliRemitRequest setBankOrders(List<BankPayOrder> orders) {
        this.bizMap.put("orderData", orders);
        return this;
    }

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }
}
