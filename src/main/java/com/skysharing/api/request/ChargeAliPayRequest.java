package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.ChargeAliPayResponse;

import java.util.ArrayList;

/**
 * @deprecated v2.4.3 停用
 */
public class ChargeAliPayRequest extends CassPayRequest<ChargeAliPayResponse> {
    public String method = "Vzhuo.AliPay.Charge";

    public ChargeAliPayRequest setOrderName(String orderName) {
        this.bizMap.put("orderName", orderName);
        return this;
    }

    public ChargeAliPayRequest setBankAccount(String bankAccount) {
        this.bizMap.put("bankAccount", bankAccount);
        return this;
    }

    public ChargeAliPayRequest setBankCardNO(String bankCardNO) {
        this.bizMap.put("bankCardNO", bankCardNO);
        return this;
    }

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

    @Override
    public ChargeAliPayResponse makeResponse(JSONObject response) {
        return new ChargeAliPayResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
