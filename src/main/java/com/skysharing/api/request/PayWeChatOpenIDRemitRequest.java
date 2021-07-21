package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Constants;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.response.PayWeChatOpenIDRemitResponse;

import java.util.List;

/**
 * <p>PayWeChatRemitRequest class.</p>
 *
 * 微信批量付款到 OpenID
 *
 * @author zhan
 */
public class PayWeChatOpenIDRemitRequest extends CassPayRequest<PayWeChatOpenIDRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.NoEsignWeChatRemit.Pay";

    /**
     * <p>Constructor for PayWeChatRemitRequest.</p>
     */
    public PayWeChatOpenIDRemitRequest() {
        this.bizMap.put("payChannelK", Constants.PAY_CHANNEL_WECHAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PayWeChatOpenIDRemitResponse makeResponse(JSONObject response) {
        return new PayWeChatOpenIDRemitResponse(response);
    }

    /**
     * <p>setOrders.</p>
     *
     * @param orders a {@link List} object.
     * @return a {@link PayWeChatOpenIDRemitRequest} object.
     */
    public PayWeChatOpenIDRemitRequest setOrders(List<WeChatOpenIDOrder> orders) {
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
