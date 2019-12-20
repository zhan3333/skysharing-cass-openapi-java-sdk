package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.PayAliRemitRequest;

public class PayAliRemitResponse extends CassPayResponse<PayAliRemitRequest> {
    public String rbUUID;

    public PayAliRemitResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
    }
}
