package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneOrderByOuterOrderSNResponse;

public class GetOneOrderByOuterOrderSNRequest extends CassPayRequest<GetOneOrderByOuterOrderSNResponse> {
    public String method = "Vzhuo.OneOrderByOuterOrderSN.Get";

    public String getMethod() {
        return this.method;
    }

    /**
     * @param orderSN 外部订单号
     */
    public void setOrderSN(String orderSN) {
        this.bizMap.put("orderSN", orderSN);
    }

    @Override
    public GetOneOrderByOuterOrderSNResponse makeResponse(JSONObject response) {
        return new GetOneOrderByOuterOrderSNResponse(response);
    }
}
