package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeBankResponse;

public class ChargeBankRequest extends CassPayRequest<ChargeBankResponse>  {
    public String method = "Vzhuo.Bank.Charge";

    public ChargeBankRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    public ChargeBankRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    public ChargeBankRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

    public ChargeBankRequest setApplyAmount(String applyAmount) {
        this.bizMap.put("applyAmount", applyAmount);
        return this;
    }

    public ChargeBankRequest setRechargePic(String rechargePic) {
        this.bizMap.put("rechargePic", rechargePic);
        return this;
    }

    @Override
    public ChargeBankResponse makeResponse(JSONObject response) {
        return new ChargeBankResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
