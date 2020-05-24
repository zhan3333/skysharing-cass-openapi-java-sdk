package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayOneWeChatRemitResponse;

import java.util.ArrayList;

public class PayOneWeChatRemitRequest extends CassPayRequest<PayOneWeChatRemitResponse> {
    public String method = "Vzhuo.OneWeChatRemit.Pay";

    public PayOneWeChatRemitRequest() {
        this.bizMap.put("payChannelK", "3");
    }

    @Override
    public PayOneWeChatRemitResponse makeResponse(JSONObject response) {
        return new PayOneWeChatRemitResponse(response);
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayOneWeChatRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    public PayOneWeChatRemitRequest setOrder(WeChatOrder order) {
        this.bizMap.put("orderData", new ArrayList<WeChatOrder>() {{
            add(order);
        }});
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
