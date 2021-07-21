package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneWeChatOpenIDRemitRequest;
import com.skysharing.api.request.PayOneWeChatRemitRequest;

/**
 * <p>PayOneWeChatOpenIDRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayOneWeChatOpenIDRemitResponse extends CassPayResponse<PayOneWeChatOpenIDRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayOneWeChatRemitResponse.</p>
     *
     * @param response a {@link JSONObject} object.
     */
    public PayOneWeChatOpenIDRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "PayOneWeChatOpenIDRemitResponse{" +
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
