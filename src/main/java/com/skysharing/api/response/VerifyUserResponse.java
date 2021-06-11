package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.VerifyUserRequest;

/**
 * <p>VerifyUserResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class VerifyUserResponse extends CassPayResponse<VerifyUserRequest> {
    /**
     * <p>Constructor for VerifyUserResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public VerifyUserResponse(JSONObject response) {
        super(response);
    }

    /** {@inheritDoc} */
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
