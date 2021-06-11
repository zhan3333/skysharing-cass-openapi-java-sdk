package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayAliRemitRequest;

/**
 * <p>PayAliRemitResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 * @see PayAliRemitRequest
 */
public class PayAliRemitResponse extends CassPayResponse<PayAliRemitRequest> {
    /**
     *
     */
    public String rbUUID;

    /**
     * <p>Constructor for PayAliRemitResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public PayAliRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }
}
