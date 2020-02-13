package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.response.PayAliRemitResponse;

import java.util.List;

public class PayAliRemitRequest extends CassPayRequest<PayAliRemitResponse> {

    public String method = "Vzhuo.AliRemit.Pay";

    @Override
    public PayAliRemitResponse makeResponse(JSONObject response) {
        return new PayAliRemitResponse(response);
    }

    public PayAliRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    public PayAliRemitRequest setOrders(List<AliPayOrder> orders) {
        this.bizMap.put("orderData", orders);
        return this;
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayAliRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
