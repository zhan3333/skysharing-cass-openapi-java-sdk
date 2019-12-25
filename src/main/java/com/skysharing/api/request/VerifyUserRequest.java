package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.VerifyUserResponse;

public class VerifyUserRequest extends CassPayRequest<VerifyUserResponse> {
    public String method = "Vzhuo.User.Verify";

    public VerifyUserRequest setName(String name) {
        this.bizMap.put("name", name);
        return this;
    }

    public VerifyUserRequest setIdCard(String idCard) {
        this.bizMap.put("id_card", idCard);
        return this;
    }

    public VerifyUserRequest setFrontImgBase64(String frontImgBase64) {
        this.bizMap.put("front_img_base64", frontImgBase64);
        return this;
    }

    public VerifyUserRequest setBackImgBase64(String backImgBase64) {
        this.bizMap.put("back_img_base64", backImgBase64);
        return this;
    }

    public VerifyUserRequest setLatitude(String latitude) {
        this.bizMap.put("latitude", latitude);
        return this;
    }

    public VerifyUserRequest setLongitude(String longitude) {
        this.bizMap.put("longitude", longitude);
        return this;
    }

    @Override
    public VerifyUserResponse makeResponse(JSONObject response) {
        return new VerifyUserResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
