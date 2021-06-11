package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.response.PayBankRemitResponse;

import java.util.List;

/**
 * <p>PayBankRemitRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class PayBankRemitRequest extends CassPayRequest<PayBankRemitResponse> {

    /**
     *
     */
    public String method = "Vzhuo.BankRemit.Pay";

    /** Constant <code>PAYEE_CHANNEL_TYPE_BANK_CARD="1"</code> */
    public static final String PAYEE_CHANNEL_TYPE_BANK_CARD = "1"; // 网商银行->银行卡
    /** Constant <code>PAYEE_CHANNEL_TYPE_ALI_PAY="2"</code> */
    public static final String PAYEE_CHANNEL_TYPE_ALI_PAY = "2"; // 网商银行->支付宝

    /** {@inheritDoc} */
    @Override
    public PayBankRemitResponse makeResponse(JSONObject response) {
        return new PayBankRemitResponse(response);
    }

    /**
     * <p>setPayChannelK.</p>
     *
     * @param payChannelK a {@link java.lang.String} object.
     * @return a {@link com.skysharing.api.request.PayBankRemitRequest} object.
     */
    public PayBankRemitRequest setPayChannelK(String payChannelK) {
        this.bizMap.put("payChannelK", payChannelK);
        return this;
    }

    /**
     * 设置合同ID
     *
     * @param contractId 合同ID
     * @return 当前类
     */
    public PayBankRemitRequest setContractID(String contractId) {
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
     * @return 当前对象
     * @param type a {@link java.lang.String} object.
     */
    public PayBankRemitRequest setPayeeChannelType(String type) {
        this.bizMap.put("payeeChannelType", type);
        return this;
    }

    /**
     * <p>setOrders.</p>
     *
     * @param orders a {@link java.util.List} object.
     * @return a {@link com.skysharing.api.request.PayBankRemitRequest} object.
     */
    public PayBankRemitRequest setOrders(List<BankPayOrder> orders) {
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
