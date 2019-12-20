package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayBankRemitResponse;

public class PayBankRemitRequest extends CassPayRequest<PayBankRemitResponse> {

    public String method = "Vzhuo.BankRemit.Pay";

    @Override
    public PayBankRemitResponse makeResponse(JSONObject response) {
        return new PayBankRemitResponse(response);
    }

    public PayBankRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    public PayBankRemitRequest setOrders(BankPayOrder[] orders) {
        this.bizMap.put("orderData", orders);
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
