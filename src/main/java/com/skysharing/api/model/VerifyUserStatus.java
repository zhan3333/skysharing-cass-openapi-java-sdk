package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

public class VerifyUserStatus {

    /**
     * 姓名
     */
    @JSONField(name = "name")
    public String name;

    /**
     * 身份证
     */
    @JSONField(name = "id_card")
    public String idCard;

    /**
     * 是否进行了实名认证
     */
    @JSONField(name = "is_verified")
    public Boolean isVerified;

    public VerifyUserStatus(String name, String idCard, Boolean isVerified) {
        this.name = name;
        this.idCard = idCard;
        this.isVerified = isVerified;
    }

    public VerifyUserStatus(String name, String idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "VerifyUserStatus{" +
                "name='" + name + '\'' +
                ", id_card='" + idCard + '\'' +
                ", is_verified=" + isVerified +
                '}';
    }
}
