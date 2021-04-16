package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeWeChatRequest;

/**
 * @deprecated v2.4.3 停用
 */
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
