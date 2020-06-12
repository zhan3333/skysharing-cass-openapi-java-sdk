package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetShowSignUrlResponse;

public class GetShowSignUrlRequest extends CassPayRequest<GetShowSignUrlResponse> {
    public String method = "Vzhuo.ShowSignUrl.Get";

    public String getMethod() {
        return this.method;
    }

    @Override
    public GetShowSignUrlResponse makeResponse(JSONObject response) {
        return new GetShowSignUrlResponse(response);
    }
}
