package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetShowSignUrlResponse;

/**
 * <p>GetShowSignUrlRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetShowSignUrlRequest extends CassPayRequest<GetShowSignUrlResponse> {
    /**
     *
     */
    public String method = "Vzhuo.ShowSignUrl.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /** {@inheritDoc} */
    @Override
    public GetShowSignUrlResponse makeResponse(JSONObject response) {
        return new GetShowSignUrlResponse(response);
    }
}
