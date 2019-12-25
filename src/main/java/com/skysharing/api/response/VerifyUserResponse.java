package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.VerifyUserRequest;

public class VerifyUserResponse extends CassPayResponse<VerifyUserRequest> {
    public boolean success;
    public String failedMessage;

    public VerifyUserResponse(JSONObject response) {
        super(response);
        this.success = this.content.getBoolean("success");
        this.failedMessage = this.content.getString("message");
    }

    @Override
    public String toString() {
        return "VerifyUserResponse{" +
                "success=" + success +
                ", failedMessage='" + failedMessage + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
