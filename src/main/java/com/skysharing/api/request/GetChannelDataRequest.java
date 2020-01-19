package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetChannelDataResponse;

public class GetChannelDataRequest extends CassPayRequest<GetChannelDataResponse> {
    public static final String BANK = "1";
    public static final String AliPay = "2";
    public static final String Wecaht = "3";

    public String method = "Vzhuo.ChannelData.Get";

    public void setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
    }

    @Override
    public GetChannelDataResponse makeResponse(JSONObject response) {
        return new GetChannelDataResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
