package com.skysharing.api.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.SignUser;
import com.skysharing.api.request.GetUserEsignStatusRequest;

import java.util.ArrayList;

/**
 * <p>GetUserEsignStatusResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetUserEsignStatusResponse extends CassPayResponse<GetUserEsignStatusRequest> {

    /**
     *
     */
    public ArrayList<SignUser> signUsers;

    /**
     * <p>Constructor for GetUserEsignStatusResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public GetUserEsignStatusResponse(JSONObject response) {
        super(response);
        this.signUsers = (ArrayList<SignUser>) JSON.parseArray(this.content.getString("users"), SignUser.class);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "GetUserEsignStatusResponse{" +
                "signUsers=" + signUsers +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
