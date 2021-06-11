package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneOrderStatusResponse;

/**
 * <p>GetOneOrderStatusRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetOneOrderStatusRequest extends CassPayRequest<GetOneOrderStatusResponse> {
    /**
     *
     */
    public String method = "Vzhuo.OneOrderStatus.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * <p>setOrderUUID.</p>
     *
     * @param orderUUID a {@link java.lang.String} object.
     */
    public void setOrderUUID(String orderUUID) {
        this.bizMap.put("orderUUID", orderUUID);
    }

    /** {@inheritDoc} */
    @Override
    public GetOneOrderStatusResponse makeResponse(JSONObject response) {
        return new GetOneOrderStatusResponse(response);
    }
}
