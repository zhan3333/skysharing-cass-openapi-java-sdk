package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeAliPayResponse;

public class ChargeAliPayRequest extends CassPayRequest<ChargeAliPayResponse> {
    public String method = "Vzhuo.AliPay.Charge";

    public ChargeAliPayRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    public ChargeAliPayRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    public ChargeAliPayRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

    public ChargeAliPayRequest setApplyAmount(String applyAmount) {
        this.bizMap.put("applyAmount", applyAmount);
        return this;
    }

    public ChargeAliPayRequest setRechargePic(String rechargePic) {
        this.bizMap.put("rechargePic", rechargePic);
        return this;
    }

    @Override
    public ChargeAliPayResponse makeResponse(JSONObject response) {
        return new ChargeAliPayResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
