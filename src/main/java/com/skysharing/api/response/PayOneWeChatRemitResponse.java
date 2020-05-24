package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneWeChatRemitRequest;

public class PayOneWeChatRemitResponse extends CassPayResponse<PayOneWeChatRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    public PayOneWeChatRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    @Override
    public String toString() {
        return "PayOneWeChatRemitResponse{" +
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
