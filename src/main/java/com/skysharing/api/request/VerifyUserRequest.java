package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.VerifyUserResponse;

public class VerifyUserRequest extends CassPayRequest<VerifyUserResponse> {
    public String method = "Vzhuo.User.Verify";

    public VerifyUserRequest setName(String name) {
        this.bizMap.put("name", name);
        return this;
    }

    public VerifyUserRequest setIdentityCard(String idCard) {
        this.bizMap.put("identityCard", idCard);
        return this;
    }

    public VerifyUserRequest setFrontImgBase64(String frontImgBase64) {
        this.bizMap.put("frontImgBase64", frontImgBase64);
        return this;
    }

    public VerifyUserRequest setBackImgBase64(String backImgBase64) {
        this.bizMap.put("backImgBase64", backImgBase64);
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

    public VerifyUserRequest setPhone(String phone) {
        this.bizMap.put("phone", phone);
        return this;
    }

    /**
     * @param FANO 收款账号 必填 最大64长度
     * @return this
     */
    public VerifyUserRequest setReceiptFANO(String FANO) {
        this.bizMap.put("receiptFANO", FANO);
        return this;
    }

    /**
     * @param type 收款类型，包括 1-银行卡，2-支付宝，4- qq
     * @return this
     */
    public VerifyUserRequest setReceiptType(Number type) {
        this.bizMap.put("receiptType", type);
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
