package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

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

    @JSONField(name = "isWechatPay")
    public void setCanWeChatPay(String canWeChatPay) {
        this.canWeChatPay = canWeChatPay.equals("Y");
    }

    @JSONField(name = "isWechatPay")
    public boolean getCanWeChatPay() {
        return this.canWeChatPay;
    }
}
