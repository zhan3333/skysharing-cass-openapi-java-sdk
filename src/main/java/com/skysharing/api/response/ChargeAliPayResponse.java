package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetBalanceRequest;

public class ChargeAliPayResponse extends CassPayResponse<GetBalanceRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    public ChargeAliPayResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
