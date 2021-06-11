package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetBalanceResponse;

/**
 * 查询商户余额: 全部余额,可用余额,锁定余额
 * <p>
 * 业务参数 payChannelK: 付款通道类型，1-银行；2-支付宝；3-微信
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetBalanceRequest extends CassPayRequest<GetBalanceResponse> {
    /** Constant <code>BANK="1"</code> */
    public static final String BANK = "1";
    /** Constant <code>AliPay="2"</code> */
    public static final String AliPay = "2";
    /** Constant <code>Wecaht="3"</code> */
    public static final String Wecaht = "3";
    /**
     *
     */
    public String method = "Vzhuo.BcBalance.Get";

    /**
     * <p>setPayChannelK.</p>
     *
     * @param payChannelK a {@link java.lang.String} object.
     */
    public void setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
    }

    /** {@inheritDoc} */
    @Override
    public GetBalanceResponse makeResponse(JSONObject response) {
        return new GetBalanceResponse(response);
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
