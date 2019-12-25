package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.VerifyUserStatus;
import com.skysharing.api.response.GetUsersVerifyStatusResponse;

public class GetUsersVerifyStatusRequest extends CassPayRequest<GetUsersVerifyStatusResponse> {
    public String method = "Vzhuo.UsersVerifyStatus.Get";

    public void setUsers(VerifyUserStatus[] users) {
        this.bizMap.put("users", users);
    }

    @Override
    public GetUsersVerifyStatusResponse makeResponse(JSONObject response) {
        return new GetUsersVerifyStatusResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
