package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeWeChatResponse;

import java.util.ArrayList;

/**
 * <p>ChargeWeChatRequest class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class ChargeWeChatRequest extends CassPayRequest<ChargeWeChatResponse> {
    /**
     *
     */
    public String method = "Vzhuo.WeChat.Charge";

    // 户名
    /**
     * <p>setOrderName.</p>
     *
     * @param orderName a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeWeChatRequest} object.
     */
    public ChargeWeChatRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    // 开户银行
    /**
     * <p>setBankAccount.</p>
     *
     * @param bankAccount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeWeChatRequest} object.
     */
    public ChargeWeChatRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    // 开户银行卡号
    /**
     * <p>setBankCardNO.</p>
     *
     * @param bankCardNO a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeWeChatRequest} object.
     */
    public ChargeWeChatRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

    // 充值金额
    /**
     * <p>setApplyAmount.</p>
     *
     * @param applyAmount a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.ChargeWeChatRequest} object.
     */
    public ChargeWeChatRequest setApplyAmount(String applyAmount) {
        this.bizMap.put("applyAmount", applyAmount);
        return this;
    }

    /**
     * 回执单照片或者扫描件(base64格式)
     *
     * @param rechargePics 照片凭据数组
     * @return 当前类
     */
    public ChargeWeChatRequest setRechargePics(ArrayList<String> rechargePics) {
        this.bizMap.put("rechargePic", rechargePics);
        return this;
    }

    /**
     * 回执单照片或者扫描件(base64格式)
     *
     * @param rechargePic 照片凭据字符串， Base64 编码
     * @return 当前类
     */
    public ChargeWeChatRequest setRechargePic(String rechargePic) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(rechargePic);
        this.bizMap.put("rechargePic", list);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public ChargeWeChatResponse makeResponse(JSONObject response) {
        return new ChargeWeChatResponse(response);
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
