package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Constants;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayOneWeChatOpenIDRemitResponse;
import com.skysharing.api.response.PayOneWeChatRemitResponse;

import java.util.ArrayList;

/**
 * <p>PayOneWeChatRemitRequest class.</p>
 * <p>
 * 微信单笔付款到 OpenID
 *
 * @author zhan
 */
public class PayOneWeChatOpenIDRemitRequest extends CassPayRequest<PayOneWeChatOpenIDRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.NoEsignOneWeChatRemit.Pay";

    /**
     * <p>Constructor for PayOneWeChatRemitRequest.</p>
     */
    public PayOneWeChatOpenIDRemitRequest() {
        this.bizMap.put("payChannelK", Constants.PAY_CHANNEL_WECHAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PayOneWeChatOpenIDRemitResponse makeResponse(JSONObject response) {
        return new PayOneWeChatOpenIDRemitResponse(response);
    }

    /**
     * <p>setOrder.</p>
     *
     * @param order a {@link WeChatOrder} object.
     * @return a {@link PayOneWeChatOpenIDRemitRequest} object.
     */
    public PayOneWeChatOpenIDRemitRequest setOrder(WeChatOpenIDOrder order) {
        this.bizMap.put("orderData", new ArrayList<WeChatOpenIDOrder>() {{
            add(order);
        }});
        return this;
    }

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getMethod() {
        return this.method;
    }
}
