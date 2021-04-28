package com.skysharing.api.model;

public class WeChatOrder {
    // 商户订单号，只能是英文字母，数字，中文以及连接符-
    // required
    // max:64
    public String orderSN;

    // 收款人电话
    // required
    // max:12
    public String phone;

    // 收款人姓名（真实姓名）
    // required
    // max:64
    public String payeeAccount;

    // 预期打款金额, 元
    // min:1.00
    // required
    public String requestPayAmount;

    // 收款人身份证号
    // no required
    // max:20
    public String identityCard;

    // 结果异步通知地址
    // required
    // max:255
    public String notifyUrl;

    /**
     * 个税(商户类型为劳务外包模式必填,值为大 于或等于 0 的数字)
     * 例如：2.00
     */
    public String tax = null;

    public WeChatOrder(String orderSN, String phone, String payeeAccount, String requestPayAmount) {
        this.orderSN = orderSN;
        this.phone = phone;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
    }

    public WeChatOrder(String orderSN, String phone, String payeeAccount, String requestPayAmount, String notifyUrl) {
        this.orderSN = orderSN;
        this.phone = phone;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        this.notifyUrl = notifyUrl;
    }

    /**
     *
     * 劳务模式加个税
     * @param orderSN
     * @param phone
     * @param payeeAccount
     * @param requestPayAmount
     * @param notifyUrl
     * @param tax
     */
    public WeChatOrder(String orderSN, String phone, String payeeAccount, String requestPayAmount, String notifyUrl, String tax) {
        this.orderSN = orderSN;
        this.phone = phone;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        this.notifyUrl = notifyUrl;
        this.tax = tax;
    }

    public WeChatOrder() {

    }

    public WeChatOrder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public WeChatOrder setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }
}
