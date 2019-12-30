package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayBankRemitRequest;
import com.skysharing.api.request.PayOneBankRemitRequest;

public class PayOneBankRemitResponse extends CassPayResponse<PayOneBankRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    public PayOneBankRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    @Override
    public String toString() {
        return "PayOneBankRemitResponse{" +
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
