package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Constants;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayOneWeChatOpenIDRemitResponse;
import com.skysharing.api.response.SingleAuthorizationOneWeChatRemitResponse;

import java.util.ArrayList;

/**
 * <p>PayOneWeChatRemitRequest class.</p>
 * <p>
 * 微信单笔付款到 OpenID
 * <p>
 * 将会校验电签
 *
 * @author zhan
 */
public class SingleAuthorizationOneWeChatRemitRequest extends CassPayRequest<SingleAuthorizationOneWeChatRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.SingleAuthorizationOneWeChatRemit.Pay";

    /**
     * <p>Constructor for PayOneWeChatRemitRequest.</p>
     */
    public SingleAuthorizationOneWeChatRemitRequest() {
        this.bizMap.put("payChannelK", Constants.PAY_CHANNEL_WECHAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SingleAuthorizationOneWeChatRemitResponse makeResponse(JSONObject response) {
        return new SingleAuthorizationOneWeChatRemitResponse(response);
    }

    /**
     * <p>setOrder.</p>
     *
     * @param order a {@link WeChatOrder} object.
     * @return a {@link SingleAuthorizationOneWeChatRemitRequest} object.
     */
    public SingleAuthorizationOneWeChatRemitRequest setOrder(WeChatOpenIDOrder order) {
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
