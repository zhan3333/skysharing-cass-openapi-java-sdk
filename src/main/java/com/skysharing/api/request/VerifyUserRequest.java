package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.VerifyUserResponse;

/**
 * <p>VerifyUserRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class VerifyUserRequest extends CassPayRequest<VerifyUserResponse> {
    /**
     *
     */
    public String method = "Vzhuo.User.Verify";

    /**
     * <p>setName.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setName(String name) {
        this.bizMap.put("name", name);
        return this;
    }

    /**
     * <p>setIdentityCard.</p>
     *
     * @param idCard a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setIdentityCard(String idCard) {
        this.bizMap.put("identityCard", idCard);
        return this;
    }

    /**
     * <p>setFrontImgBase64.</p>
     *
     * @param frontImgBase64 a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setFrontImgBase64(String frontImgBase64) {
        this.bizMap.put("frontImgBase64", frontImgBase64);
        return this;
    }

    /**
     * <p>setBackImgBase64.</p>
     *
     * @param backImgBase64 a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setBackImgBase64(String backImgBase64) {
        this.bizMap.put("backImgBase64", backImgBase64);
        return this;
    }

    /**
     * <p>setLatitude.</p>
     *
     * @param latitude a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setLatitude(String latitude) {
        this.bizMap.put("latitude", latitude);
        return this;
    }

    /**
     * <p>setLongitude.</p>
     *
     * @param longitude a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setLongitude(String longitude) {
        this.bizMap.put("longitude", longitude);
        return this;
    }

    /**
     * <p>setPhone.</p>
     *
     * @param phone a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.VerifyUserRequest} object.
     */
    public VerifyUserRequest setPhone(String phone) {
        this.bizMap.put("phone", phone);
        return this;
    }

    /**
     * <p>setReceiptFANO.</p>
     *
     * @param FANO 收款账号 必填 最大64长度
     * @return this
     */
    public VerifyUserRequest setReceiptFANO(String FANO) {
        this.bizMap.put("receiptFANO", FANO);
        return this;
    }

    /**
     * <p>setReceiptType.</p>
     *
     * @param type 收款类型，包括 1-银行卡，2-支付宝，4- qq
     * @return this
     */
    public VerifyUserRequest setReceiptType(Number type) {
        this.bizMap.put("receiptType", type);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public VerifyUserResponse makeResponse(JSONObject response) {
        return new VerifyUserResponse(response);
    }

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }
}
