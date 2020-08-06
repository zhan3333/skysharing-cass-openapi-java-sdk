package com.skysharing.api.request;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetUsersVerifyStatusResponse;

import java.util.List;

public class GetUsersVerifyStatusRequest extends CassPayRequest<GetUsersVerifyStatusResponse> {
    public static class Item {
        public String identityCard;
        public String receiptFANO;
        public Number receiptType;

        public Item(String identityCard, String receiptFANO, Number receiptType) {
            this.identityCard = identityCard;
            this.receiptFANO = receiptFANO;
            this.receiptType = receiptType;
        }
    }

    public String method = "Vzhuo.UsersVerifyStatus.Get";

    /**
     * 设置要查询的身份证号码数组
     *
     * @param identityCards 设置要查询的身份证号码数组
     */
    public void setIdentityCards(List<String> identityCards) {
        this.bizMap.put("identityCards", identityCards);
    }

    public GetUsersVerifyStatusRequest addItem(Item i) {
        JSONArray arr = this.bizMap.getJSONArray("items");
        if (arr == null) {
            arr = new JSONArray();
        }
        arr.add(i);
        this.bizMap.put("items", arr);
        return this;
    }

    @Override
    public GetUsersVerifyStatusResponse makeResponse(JSONObject response) {
        return new GetUsersVerifyStatusResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}

