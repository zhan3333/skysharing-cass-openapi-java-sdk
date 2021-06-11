package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeBankResponse;

/**
 * <p>ChargeBankRequest class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeBankRequest extends CassPayRequest<ChargeBankResponse> {
    /**
     *
     */
    public String method = "Vzhuo.Bank.Charge";

    /**
     * <p>setOrderName.</p>
     *
     * @param orderName a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeBankRequest} object.
     */
    public ChargeBankRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    /**
     * <p>setBankAccount.</p>
     *
     * @param bankAccount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeBankRequest} object.
     */
    public ChargeBankRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    /**
     * <p>setBankCardNO.</p>
     *
     * @param bankCardNO a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeBankRequest} object.
     */
    public ChargeBankRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

    /**
     * <p>setApplyAmount.</p>
     *
     * @param applyAmount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeBankRequest} object.
     */
    public ChargeBankRequest setApplyAmount(String applyAmount) {
        this.bizMap.put("applyAmount", applyAmount);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public ChargeBankResponse makeResponse(JSONObject response) {
        return new ChargeBankResponse(response);
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
