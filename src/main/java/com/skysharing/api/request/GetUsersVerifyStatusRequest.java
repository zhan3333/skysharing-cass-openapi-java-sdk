package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetUsersVerifyStatusResponse;

import java.util.List;

public class GetUsersVerifyStatusRequest extends CassPayRequest<GetUsersVerifyStatusResponse> {
    public String method = "Vzhuo.UsersVerifyStatus.Get";

    /**
     * 设置要查询的身份证号码数组
     * @param identityCards 设置要查询的身份证号码数组
     */
    public void setIdentityCards(List<String> identityCards) {
        this.bizMap.put("identityCards", identityCards);
    }

    @Override
    public GetUsersVerifyStatusResponse makeResponse(JSONObject response) {
        return new GetUsersVerifyStatusResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
