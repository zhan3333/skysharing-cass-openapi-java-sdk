package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayWeChatOpenIDRemitRequest;
import com.skysharing.api.request.PayWeChatRemitRequest;

/**
 * <p>PayWeChatOpenIDRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayWeChatOpenIDRemitResponse extends CassPayResponse<PayWeChatOpenIDRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayWeChatRemitResponse.</p>
     *
     * @param response a {@link JSONObject} object.
     */
    public PayWeChatOpenIDRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PayWeChatOpenIDRemitResponse{" +
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
