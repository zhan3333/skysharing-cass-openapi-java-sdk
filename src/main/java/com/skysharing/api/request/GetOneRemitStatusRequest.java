package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetOneRemitStatusResponse;

/**
 * <p>GetOneRemitStatusRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetOneRemitStatusRequest extends CassPayRequest<GetOneRemitStatusResponse> {
    /**
     *
     */
    public String method = "Vzhuo.OneRemitStatus.Get";

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }

    /**
     * <p>setRbUUID.</p>
     *
     * @param rbUUID a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.GetOneRemitStatusRequest} object.
     */
    public GetOneRemitStatusRequest setRbUUID(String rbUUID) {
        this.bizMap.put("rbUUID", rbUUID);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public GetOneRemitStatusResponse makeResponse(JSONObject response) {
        return new GetOneRemitStatusResponse(response);
    }
}
