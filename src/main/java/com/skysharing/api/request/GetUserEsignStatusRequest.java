package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetUserEsignStatusResponse;

import java.util.ArrayList;

public class GetUserEsignStatusRequest extends CassPayRequest<GetUserEsignStatusResponse> {
    public String method = "Vzhuo.UsersEsignStatus.Get";

    public String getMethod() {
        return this.method;
    }

    public GetUserEsignStatusRequest addPhone(String phone) {
        ArrayList<String> list = new ArrayList<>(1);
        list.add(phone);
        this.bizMap.put("phones", list);
        return this;
    }

    public GetUserEsignStatusRequest setPhones(ArrayList<String> phones) {
        this.bizMap.put("phones", phones);
        return this;
    }

    @Override
    public GetUserEsignStatusResponse makeResponse(JSONObject response) {
        return new GetUserEsignStatusResponse(response);
    }
}
