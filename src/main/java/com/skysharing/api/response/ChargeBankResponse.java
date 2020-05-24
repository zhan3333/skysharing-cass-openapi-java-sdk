package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeBankRequest;

public class ChargeBankResponse extends CassPayResponse<ChargeBankRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    public ChargeBankResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
