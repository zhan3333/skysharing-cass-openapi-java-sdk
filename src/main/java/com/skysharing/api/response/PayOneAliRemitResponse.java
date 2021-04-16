package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneAliRemitRequest;

/**
 * @see PayOneAliRemitRequest
 */
public class PayOneAliRemitResponse extends CassPayResponse<PayOneAliRemitRequest> {
    public String rbUUID;

    public PayOneAliRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    @Override
    public String toString() {
        return "PayOneAliRemitResponse{" +
                "rbUUID='" + rbUUID + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
