package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.response.PayWeChatRemitResponse;

import java.util.List;

// 微信批量打款
/**
 * <p>PayWeChatRemitRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayWeChatRemitRequest extends CassPayRequest<PayWeChatRemitResponse> {
    /**
     *
     */
    public String method = "Vzhuo.WeChatRemit.Pay";

    /**
     * <p>Constructor for PayWeChatRemitRequest.</p>
     */
    public PayWeChatRemitRequest() {
        this.bizMap.put("payChannelK", 3);
    }

    /** {@inheritDoc} */
    @Override
    public PayWeChatRemitResponse makeResponse(JSONObject response) {
        return new PayWeChatRemitResponse(response);
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayWeChatRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    /**
     * <p>setOrders.</p>
     *
     * @param orders a {@link java.util.List} object.
     * @return a {@link com.skysharing.api.request.PayWeChatRemitRequest} object.
     */
    public PayWeChatRemitRequest setOrders(List<WeChatOrder> orders) {
        this.bizMap.put("orderData", orders);
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
