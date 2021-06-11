package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeBankRequest;

/**
 * <p>ChargeBankResponse class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeBankResponse extends CassPayResponse<ChargeBankRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    /**
     * <p>Constructor for ChargeBankResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public ChargeBankResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
