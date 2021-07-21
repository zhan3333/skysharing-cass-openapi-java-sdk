package com.skysharing.api.model;

/**
 * <p>WeChatOrder class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class WeChatOpenIDOrder {

    /**
     * 用户微信账号 OpenID，用于收款
     * required
     * max:23
     */
    public String receiptFANO;

    // 商户订单号，只能是英文字母，数字，中文以及连接符-
    // required
    // max:64
    public String orderSN;

    // 收款人电话
    // no required
    // max:12
    public String phone;

    // 收款人姓名（真实姓名）
    // no required
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
     * no required
     */
    public String tax = null;

    /**
     * <p>Constructor for WeChatOrder.</p>
     *
     * @param orderSN          商户订单号
     * @param weChatOpenID     收款人微信 OpenID
     * @param requestPayAmount 付款金额，小数点后2位
     * @param notifyURL        订单完成异步通知地址
     */
    public WeChatOpenIDOrder(String orderSN, String weChatOpenID, String requestPayAmount, String notifyURL) {
        this.orderSN = orderSN;
        this.receiptFANO = weChatOpenID;
        this.requestPayAmount = requestPayAmount;
        this.notifyUrl = notifyURL;
    }

    /**
     * <p>Setter for the field <code>identityCard</code>.</p>
     *
     * @param identityCard a {@link String} object.
     * @return a {@link WeChatOpenIDOrder} object.
     */
    public WeChatOpenIDOrder setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    /**
     * @param payeeAccount 收款人姓名
     * @return this
     */
    public WeChatOpenIDOrder setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
        return this;
    }

    /**
     * @param phone 手机号
     */
    public WeChatOpenIDOrder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * @param tax 个税
     * @return this
     */
    public WeChatOpenIDOrder setTax(String tax) {
        this.tax = tax;
        return this;
    }
}
