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
    public String identityCard = null;

    /**
     * 个税(商户类型为劳务外包模式必填,值为大 于或等于 0 的数字)
     * 例如：2.00
     */
    public String tax = null;

    /**
     * 异步通知地址： http://xxx.xxx.cn/xx/asynNotify.htm
     */
    public String notifyUrl = "";

    public AliPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
    }

    /**
     * 设置订单身份证号
     *
     * @param identityCard 身份证号
     * @return this
     */
    public AliPayOrder setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    /**
     * 设置订单个税
     *
     * @param tax 个税
     * @return this
     */
    public AliPayOrder setTax(String tax) {
        this.tax = tax;
        return this;
    }

    /**
     * 设置回调地址
     *
     * @param notifyUrl 接收通知的地址
     * @return 当前对象
     */
    public AliPayOrder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
