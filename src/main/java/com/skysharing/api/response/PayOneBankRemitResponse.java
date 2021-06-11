package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayOneBankRemitRequest;

/**
 * <p>PayOneBankRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayOneBankRemitResponse extends CassPayResponse<PayOneBankRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayOneBankRemitResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public PayOneBankRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /** {@inheritDoc} */
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
