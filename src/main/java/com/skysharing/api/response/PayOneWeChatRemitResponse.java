package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneWeChatRemitRequest;

/**
 * <p>PayOneWeChatRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayOneWeChatRemitResponse extends CassPayResponse<PayOneWeChatRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayOneWeChatRemitResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public PayOneWeChatRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /** {@inheritDoc} */
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
