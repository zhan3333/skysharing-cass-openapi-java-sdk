package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.BalanceAliPay;
import com.skysharing.api.model.BalanceBank;
import com.skysharing.api.request.GetBalanceRequest;

public class GetBalanceResponse extends CassPayResponse<GetBalanceRequest> {
    public BalanceBank bank = new BalanceBank();

    public BalanceAliPay alipay = new BalanceAliPay();

    public GetBalanceResponse(JSONObject response) {
        super(response);
        if (this.content.containsKey("bank")) {
            JSONObject bank = this.content.getJSONObject("bank");
            this.bank.bankAccout = bank.getString("bankAccount");
            this.bank.lockedAmt = bank.getString("lockedAmt");
            this.bank.canUseAmt = bank.getString("canUseAmt");
            this.bank.childFAbalance = bank.getString("childFAbalance");
            this.bank.mandatoryName = bank.getString("mandatoryName");
        }
        if (this.content.containsKey("alipay")) {
            JSONObject alipay = this.content.getJSONObject("alipay");
            this.alipay.lockedAmt = alipay.getString("lockedAmt");
            this.alipay.canUseAmt = alipay.getString("canUseAmt");
            this.alipay.childFAbalance = alipay.getString("childFAbalance");
        }
    }

    @Override
    public String toString() {
        return "GetBalanceResponse{" +
                "bank=" + bank +
                ", alipay=" + alipay +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
