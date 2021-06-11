package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneOrderByOuterOrderSNResponse;

/**
 * <p>GetOneOrderByOuterOrderSNRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetOneOrderByOuterOrderSNRequest extends CassPayRequest<GetOneOrderByOuterOrderSNResponse> {
    /**
     *
     */
    public String method = "Vzhuo.OneOrderByOuterOrderSN.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * <p>setOrderSN.</p>
     *
     * @param orderSN 外部订单号
     */
    public void setOrderSN(String orderSN) {
        this.bizMap.put("orderSN", orderSN);
    }

    /** {@inheritDoc} */
    @Override
    public GetOneOrderByOuterOrderSNResponse makeResponse(JSONObject response) {
        return new GetOneOrderByOuterOrderSNResponse(response);
    }
}
