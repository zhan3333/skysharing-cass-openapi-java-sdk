package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetUserEsignStatusResponse;

import java.util.ArrayList;

/**
 * <p>GetUserEsignStatusRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetUserEsignStatusRequest extends CassPayRequest<GetUserEsignStatusResponse> {
    /**
     *
     */
    public String method = "Vzhuo.UsersEsignStatus.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * <p>addPhone.</p>
     *
     * @param phone a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.GetUserEsignStatusRequest} object.
     */
    public GetUserEsignStatusRequest addPhone(String phone) {
        ArrayList<String> list = new ArrayList<>(1);
        list.add(phone);
        this.bizMap.put("phones", list);
        return this;
    }

    /**
     * <p>setPhones.</p>
     *
     * @param phones a {@link java.util.ArrayList} object.
     * @return a {@link com.skysharing.api.request.GetUserEsignStatusRequest} object.
     */
    public GetUserEsignStatusRequest setPhones(ArrayList<String> phones) {
        this.bizMap.put("phones", phones);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public GetUserEsignStatusResponse makeResponse(JSONObject response) {
        return new GetUserEsignStatusResponse(response);
    }
}
