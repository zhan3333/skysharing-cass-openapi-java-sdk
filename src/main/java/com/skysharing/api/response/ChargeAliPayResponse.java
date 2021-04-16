package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeAliPayRequest;

/**
 * @deprecated v2.4.3 停用
 */
public class ChargeAliPayResponse extends CassPayResponse<ChargeAliPayRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    public ChargeAliPayResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
