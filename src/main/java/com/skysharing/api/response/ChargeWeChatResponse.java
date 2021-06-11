package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.ChargeWeChatRequest;

/**
 * <p>ChargeWeChatResponse class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeWeChatResponse extends CassPayResponse<ChargeWeChatRequest> {
    /**
     * 流水号
     */
    public String rechargeSBSN;

    /**
     * <p>Constructor for ChargeWeChatResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public ChargeWeChatResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
    }
}
