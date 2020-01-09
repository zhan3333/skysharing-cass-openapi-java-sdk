package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.response.PayOneAliRemitResponse;

import java.util.ArrayList;

public class PayOneAliRemitRequest extends CassPayRequest<PayOneAliRemitResponse> {

    public String method = "Vzhuo.OneAliRemit.Pay";

    @Override
    public PayOneAliRemitResponse makeResponse(JSONObject response) {
        return new PayOneAliRemitResponse(response);
    }

    public PayOneAliRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    public PayOneAliRemitRequest setOrder(AliPayOrder order) {
        this.bizMap.put("orderData", new ArrayList<AliPayOrder>(){{
            add(order);
        }});
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
