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

    public WeChatOrder(String orderSN, String phone, String payeeAccount, String requestPayAmount, String notifyUrl) {
        this.orderSN = orderSN;
        this.phone = phone;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        this.notifyUrl = notifyUrl;
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
