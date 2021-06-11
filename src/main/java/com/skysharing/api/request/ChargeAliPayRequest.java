package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeAliPayResponse;

import java.util.ArrayList;

/**
 * <p>ChargeAliPayRequest class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeAliPayRequest extends CassPayRequest<ChargeAliPayResponse> {
    public String method = "Vzhuo.AliPay.Charge";

    /**
     * <p>setOrderName.</p>
     *
     * @param orderName a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeAliPayRequest} object.
     */
    public ChargeAliPayRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    /**
     * <p>setBankAccount.</p>
     *
     * @param bankAccount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeAliPayRequest} object.
     */
    public ChargeAliPayRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    /**
     * <p>setBankCardNO.</p>
     *
     * @param bankCardNO a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeAliPayRequest} object.
     */
    public ChargeAliPayRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

    /**
     * <p>setApplyAmount.</p>
     *
     * @param applyAmount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeAliPayRequest} object.
     */
    public ChargeAliPayRequest setApplyAmount(String applyAmount) {
        this.bizMap.put("applyAmount", applyAmount);
        return this;
    }

    /**
     * 添加多个照片凭据
     *
     * @param rechargePics 照片凭据数组
     * @return 当前类
     */
    public ChargeAliPayRequest setRechargePics(ArrayList<String> rechargePics) {
        this.bizMap.put("rechargePic", rechargePics);
        return this;
    }

    /**
     * 添加单个照片凭据
     *
     * @param rechargePic 照片凭据字符串， Base64 编码
     * @return 当前类
     */
    public ChargeAliPayRequest setRechargePic(String rechargePic) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(rechargePic);
        this.bizMap.put("rechargePic", list);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public ChargeAliPayResponse makeResponse(JSONObject response) {
        return new ChargeAliPayResponse(response);
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
