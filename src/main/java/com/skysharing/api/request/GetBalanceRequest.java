package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetBalanceResponse;

/**
 * 查询商户余额: 全部余额,可用余额,锁定余额
 * <p>
 * 业务参数 payChannelK: 付款通道类型，1-银行；2-支付宝；3-微信
 */
public class GetBalanceRequest extends CassPayRequest<GetBalanceResponse> {
    public static final String BANK = "1";
    public static final String AliPay = "2";
    public static final String Wecaht = "3";

    public String method = "Vzhuo.BcBalance.Get";

    public void setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
    }

    @Override
    public GetBalanceResponse makeResponse(JSONObject response) {
        return new GetBalanceResponse(response);
    }

    public String getMethod() {
        return this.method;
    }
}
