package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.VerifyUserRequest;

public class VerifyUserResponse extends CassPayResponse<VerifyUserRequest> {
    public VerifyUserResponse(JSONObject response) {
        super(response);
    }

    @Override
    public String toString() {
        return "VerifyUserResponse{" +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
