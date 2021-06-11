package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayBankRemitRequest;

/**
 * <p>PayBankRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayBankRemitResponse extends CassPayResponse<PayBankRemitRequest> {
    /**
     * 批次UUID
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayBankRemitResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public PayBankRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }

    /** {@inheritDoc} */
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
