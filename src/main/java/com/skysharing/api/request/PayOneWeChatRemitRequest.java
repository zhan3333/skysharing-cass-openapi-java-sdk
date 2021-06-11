package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayOneWeChatRemitResponse;

import java.util.ArrayList;

/**
 * <p>PayOneWeChatRemitRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayOneWeChatRemitRequest extends CassPayRequest<PayOneWeChatRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.OneWeChatRemit.Pay";

    /**
     * <p>Constructor for PayOneWeChatRemitRequest.</p>
     */
    public PayOneWeChatRemitRequest() {
        this.bizMap.put("payChannelK", "3");
    }

    /** {@inheritDoc} */
    @Override
    public PayOneWeChatRemitResponse makeResponse(JSONObject response) {
        return new PayOneWeChatRemitResponse(response);
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayOneWeChatRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    /**
     * <p>setOrder.</p>
     *
     * @param order a {@link com.skysharing.api.model.WeChatOrder} object.
     * @return a {@link com.skysharing.api.request.PayOneWeChatRemitRequest} object.
     */
    public PayOneWeChatRemitRequest setOrder(WeChatOrder order) {
        this.bizMap.put("orderData", new ArrayList<WeChatOrder>() {{
            add(order);
        }});
        return this;
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
