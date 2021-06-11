package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>SignUser class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class SignUser {
    // 用户姓名
    @JSONField(name = "payeeAccount")
    public String name;
    // 用户手机号
    @JSONField(name = "phone")
    public String phone;
    // 电签状态：Y-已电签，N-未电签
    @JSONField(name = "esignStatus")
    public String status;
    // 是否可以微信付款
    @JSONField(serialize = false)
    private boolean canWeChatPay;

    /**
     * <p>Setter for the field <code>canWeChatPay</code>.</p>
     *
     * @param canWeChatPay a {@link java.lang.String} object.
     */
    @JSONField(name = "isWechatPay")
    public void setCanWeChatPay(String canWeChatPay) {
        this.canWeChatPay = canWeChatPay.equals("Y");
    }

    /**
     * <p>Getter for the field <code>canWeChatPay</code>.</p>
     *
     * @return a boolean.
     */
    @JSONField(name = "isWechatPay")
    public boolean getCanWeChatPay() {
        return this.canWeChatPay;
    }
}
