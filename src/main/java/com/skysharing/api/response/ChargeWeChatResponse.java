package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeWeChatRequest;

public class ChargeWeChatResponse extends CassPayResponse<ChargeWeChatRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    public ChargeWeChatResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
