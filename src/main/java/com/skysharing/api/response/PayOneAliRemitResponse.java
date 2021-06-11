package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneAliRemitRequest;

/**
 * <p>PayOneAliRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 * @see PayOneAliRemitRequest
 */
public class PayOneAliRemitResponse extends CassPayResponse<PayOneAliRemitRequest> {
    /**
     *
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayOneAliRemitResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public PayOneAliRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /**
     * {@inheritDoc}
     */
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
