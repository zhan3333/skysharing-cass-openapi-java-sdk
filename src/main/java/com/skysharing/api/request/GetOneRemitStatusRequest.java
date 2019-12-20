package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneRemitStatusResponse;

public class GetOneRemitStatusRequest extends CassPayRequest<GetOneRemitStatusResponse> {

    public String method = "Vzhuo.OneRemitStatus.Get";

    public String getMethod() {
        return this.method;
    }

    public GetOneRemitStatusRequest setRbUUID (String rbUUID) {
        this.bizMap.put("rbUUID", rbUUID);
        return this;
    }

    @Override
    public GetOneRemitStatusResponse makeResponse(JSONObject response) {
        return  new GetOneRemitStatusResponse(response);
    }
}
