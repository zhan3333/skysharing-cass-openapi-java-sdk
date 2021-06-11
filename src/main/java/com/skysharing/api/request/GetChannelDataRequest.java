package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetChannelDataResponse;

/**
 * <p>GetChannelDataRequest class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class GetChannelDataRequest extends CassPayRequest<GetChannelDataResponse> {
    /** Constant <code>BANK="1"</code> */
    public static final String BANK = "1";
    /** Constant <code>AliPay="2"</code> */
    public static final String AliPay = "2";
    /** Constant <code>Wecaht="3"</code> */
    public static final String Wecaht = "3";
    /**
     *
     */
    public String method = "Vzhuo.ChannelData.Get";

    /**
     * <p>setPayChannelK.</p>
     *
     * @param payChannelK a {@link java.lang.String} object.
     */
    public void setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
    }

    /** {@inheritDoc} */
    @Override
    public GetChannelDataResponse makeResponse(JSONObject response) {
        return new GetChannelDataResponse(response);
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
