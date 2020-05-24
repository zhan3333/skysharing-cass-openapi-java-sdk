package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayWeChatRemitResponse;

import java.util.List;

// 微信批量打款
public class PayWeChatRemitRequest extends CassPayRequest<PayWeChatRemitResponse> {
    public String method = "Vzhuo.WeChatRemit.Pay";

    public PayWeChatRemitRequest() {
        this.bizMap.put("payChannelK", 3);
    }

    @Override
    public PayWeChatRemitResponse makeResponse(JSONObject response) {
        return new PayWeChatRemitResponse(response);
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayWeChatRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    public PayWeChatRemitRequest setOrders(List<WeChatOrder> orders) {
        this.bizMap.put("orderData", orders);
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
