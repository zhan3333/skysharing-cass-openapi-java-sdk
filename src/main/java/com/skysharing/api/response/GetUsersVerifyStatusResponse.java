package com.skysharing.api.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.VerifyUserStatus;
import com.skysharing.api.request.GetUsersVerifyStatusRequest;

import java.util.List;

public class GetUsersVerifyStatusResponse extends CassPayResponse<GetUsersVerifyStatusRequest> {
    public List<VerifyUserStatus> users;

    public GetUsersVerifyStatusResponse(JSONObject response) {
        super(response);
        this.users = JSON.parseArray(this.content.getString("users"), VerifyUserStatus.class);
    }

    @Override
    public String toString() {
        return "GetUsersVerifyStatusResponse{" +
                "users=" + users +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
