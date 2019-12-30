package com.skysharing.api.model;

public class AliPayOrder {
    /**
     * 商户订单号
     * required
     */
    public String orderSN;

    /**
     * 收款人支付宝账号
     * required
     */
    public String receiptFANO;

    /**
     * 收款人户名(真实姓名)
     * required
     */
    public String payeeAccount;

    /**
     * 预期打款金额
     * required
     */
    public String requestPayAmount;

    /**
     * 身份证号, 在需要认证用户实名信息的情况下需要输入
     */
    public String identityCard;

    /**
     * name(合同名称)和 description(合同描述)
     */
    public PayOrderData data = new PayOrderData();

    public AliPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
    }

    /**
     * 设置订单身份证号
     * @param identityCard 身份证号
     * @return 原对象
     */
    public AliPayOrder setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }
}
