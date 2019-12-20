package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneOrderStatusResponse;

public class GetOneOrderStatusRequest extends CassPayRequest<GetOneOrderStatusResponse> {
    public String method = "Vzhuo.OneOrderStatus.Get";

    public String getMethod() {
        return this.method;
    }

    public void setOrderUUID (String orderUUID) {
        this.bizMap.put("orderUUID", orderUUID);
    }

    @Override
    public GetOneOrderStatusResponse makeResponse(JSONObject response) {
        return  new GetOneOrderStatusResponse(response);
    }
}
