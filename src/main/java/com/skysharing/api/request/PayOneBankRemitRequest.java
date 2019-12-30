package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayBankRemitResponse;
import com.skysharing.api.response.PayOneBankRemitResponse;

import java.util.ArrayList;
import java.util.List;

public class PayOneBankRemitRequest extends CassPayRequest<PayOneBankRemitResponse> {

    public String method = "Vzhuo.OneBankRemit.Pay";

    @Override
    public PayOneBankRemitResponse makeResponse(JSONObject response) {
        return new PayOneBankRemitResponse(response);
    }

    public PayOneBankRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    public PayOneBankRemitRequest setOrder(BankPayOrder order) {
        this.bizMap.put("orderData", new ArrayList<BankPayOrder>(){{
            add(order);
        }});
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
