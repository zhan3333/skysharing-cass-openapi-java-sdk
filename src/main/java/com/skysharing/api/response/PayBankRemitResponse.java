package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayBankRemitRequest;

public class PayBankRemitResponse extends CassPayResponse<PayBankRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    public PayBankRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    @Override
    public String toString() {
        return "PayBankRemitResponse{" +
                "rbUUID='" + rbUUID + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                ", sign='" + sign + '\'' +
                ", request=" + request +
                ", raw=" + raw +
                '}';
    }
}
