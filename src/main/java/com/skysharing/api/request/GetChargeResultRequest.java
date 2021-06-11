package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetChargeResultResponse;

/**
 * <p>GetChargeResultRequest class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class GetChargeResultRequest extends CassPayRequest<GetChargeResultResponse> {
    /**
     *
     */
    public String method = "Vzhuo.ApplyChargeResult.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * <p>setRechargeSBSN.</p>
     *
     * @param rechargeSBSN a {@link java.lang.String} object.
     */
    public void setRechargeSBSN(String rechargeSBSN) {
        this.bizMap.put("rechargeSBSN", rechargeSBSN);
    }

    /** {@inheritDoc} */
    @Override
    public GetChargeResultResponse makeResponse(JSONObject response) {
        return new GetChargeResultResponse(response);
    }
}
