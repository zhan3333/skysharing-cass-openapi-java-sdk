package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayOneBankRemitResponse;

import java.util.ArrayList;

public class PayOneBankRemitRequest extends CassPayRequest<PayOneBankRemitResponse> {

    public String method = "Vzhuo.OneBankRemit.Pay";

    public static final String PAYEE_CHANNEL_TYPE_BANK_CARD = "1"; // 网商银行->银行卡
    public static final String PAYEE_CHANNEL_TYPE_ALI_PAY = "2"; // 网商银行->支付宝

    @Override
    public PayOneBankRemitResponse makeResponse(JSONObject response) {
        return new PayOneBankRemitResponse(response);
    }

    public PayOneBankRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayOneBankRemitRequest setContractID(String contractId) {
        this.bizMap.put("contractID", contractId);
        return this;
    }

    /**
     * 设置网商银行参数
     * <p>
     * 网商银行必填，收款通道：
     * 网商银行支持，1-银行卡，2-支付宝；
     * 其他银行仅支持，1-银行卡
     *
     * @return
     */
    public PayOneBankRemitRequest setPayeeChannelType(String type) {
        this.bizMap.put("payeeChannelType", type);
        return this;
    }

    public PayOneBankRemitRequest setOrder(BankPayOrder order) {
        this.bizMap.put("orderData", new ArrayList<BankPayOrder>() {{
            add(order);
        }});
        return this;
    }

    public String getMethod() {
        return this.method;
    }
}
