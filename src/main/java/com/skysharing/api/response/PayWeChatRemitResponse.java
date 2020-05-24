package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayWeChatRemitRequest;

public class PayWeChatRemitResponse extends CassPayResponse<PayWeChatRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    public PayWeChatRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    @Override
    public String toString() {
        return "PayWeChatRemitResponse{" +
                "rbUUID='" + rbUUID + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                ", sign='" + sign + '\'' +
                ", request=" + request +
                ", raw=" + raw +
                ", vzhuoPublicKey=" + vzhuoPublicKey +
                '}';
    }
}
