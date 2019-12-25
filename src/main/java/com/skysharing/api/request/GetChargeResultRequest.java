package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetChargeResultResponse;

public class GetChargeResultRequest extends CassPayRequest<GetChargeResultResponse>  {
    public String method = "Vzhuo.ApplyChargeResult.Get";

    public String getMethod() {
        return this.method;
    }

    public void setRechargeSBSN (String rechargeSBSN) {
        this.bizMap.put("rechargeSBSN", rechargeSBSN);
    }

    @Override
    public GetChargeResultResponse makeResponse(JSONObject response) {
        return  new GetChargeResultResponse(response);
    }
}
