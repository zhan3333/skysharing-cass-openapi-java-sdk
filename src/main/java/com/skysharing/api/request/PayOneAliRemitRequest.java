package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayOneAliRemitResponse;

import java.util.ArrayList;

/**
 * 支付宝单笔付款
 *
 * @see PayOneAliRemitResponse
 */
public class PayOneAliRemitRequest extends CassPayRequest<PayOneAliRemitResponse> {

    public String method = "Vzhuo.OneAliRemit.Pay";

    @Override
    public PayOneAliRemitResponse makeResponse(JSONObject response) {
        return new PayOneAliRemitResponse(response);
    }

    /**
     * 设置付款通道
     *
     * @param payChannelK 付款通道 key
     * @return this
     * @see com.skysharing.api.Constants
     */
    public PayOneAliRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    /**
     * 设置收款通道
     *
     * @param k 收款通道 key
     * @return this
     * @see com.skysharing.api.Constants
     */
    public PayOneAliRemitRequest setPayeeChannelType(String k) {
        this.bizMap.put("payeeChannelType", k);
        return this;
    }

    /**
     * 设置订单对象
     *
     * @param order 支付宝收款订单
     * @return this
     */
    public PayOneAliRemitRequest setOrder(AliPayOrder order) {
        this.bizMap.put("orderData", new ArrayList<AliPayOrder>() {{
            add(order);
        }});
        return this;
    }

    /**
     * 设置订单对象
     *
     * @param order 银行卡收款订单
     * @return this
     */
    public PayOneAliRemitRequest setOrder(BankPayOrder order) {
        this.bizMap.put("orderData", new ArrayList<BankPayOrder>() {{
            add(order);
        }});
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
