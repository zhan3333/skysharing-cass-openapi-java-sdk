package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Constants;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.response.PayWeChatOpenIDRemitResponse;
import com.skysharing.api.response.SingleAuthorizationWeChatRemitResponse;

import java.util.List;

/**
 * <p>PayWeChatRemitRequest class.</p>
 * <p>
 * 微信批量付款到 OpenID
 * <p>
 * 将会校验电签
 *
 * @author zhan
 */
public class SingleAuthorizationWeChatRemitRequest extends CassPayRequest<SingleAuthorizationWeChatRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.SingleAuthorizationWeChatRemit.Pay";

    /**
     * <p>Constructor for PayWeChatRemitRequest.</p>
     */
    public SingleAuthorizationWeChatRemitRequest() {
        this.bizMap.put("payChannelK", Constants.PAY_CHANNEL_WECHAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SingleAuthorizationWeChatRemitResponse makeResponse(JSONObject response) {
        return new SingleAuthorizationWeChatRemitResponse(response);
    }

    /**
     * <p>setOrders.</p>
     *
     * @param orders a {@link List} object.
     * @return a {@link SingleAuthorizationWeChatRemitRequest} object.
     */
    public SingleAuthorizationWeChatRemitRequest setOrders(List<WeChatOpenIDOrder> orders) {
        this.bizMap.put("orderData", orders);
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
