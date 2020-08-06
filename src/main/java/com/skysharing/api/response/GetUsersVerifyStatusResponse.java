package com.skysharing.api.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetUsersVerifyStatusRequest;

import java.util.List;

public class GetUsersVerifyStatusResponse extends CassPayResponse<GetUsersVerifyStatusRequest> {
    public static class Item {
        public String identityCard;
        public String receiptFANO;
        public String receiptType;
        public String status;
        public boolean exists;
        public String verifiedAt;
        public String failedMessage;
    }

    public List<Item> items;

    public GetUsersVerifyStatusResponse(JSONObject response) {
        super(response);
        this.items = JSON.parseArray(this.content.getString("items"), Item.class);
    }

    @Override
    public String toString() {
        return "GetUsersVerifyStatusResponse{" +
                "items=" + items +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
