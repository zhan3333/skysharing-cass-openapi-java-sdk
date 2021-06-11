package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeAliPayRequest;

/**
 * <p>ChargeAliPayResponse class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeAliPayResponse extends CassPayResponse<ChargeAliPayRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    /**
     * <p>Constructor for ChargeAliPayResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public ChargeAliPayResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
