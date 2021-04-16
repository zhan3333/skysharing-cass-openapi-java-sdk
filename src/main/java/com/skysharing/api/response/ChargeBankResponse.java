package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeBankRequest;

/**
 * @deprecated v2.4.3 停用
 */
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
