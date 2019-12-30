package com.skysharing.api.model;

import com.alibaba.fastjson.annotation.JSONField;

public class VerifyUserStatus {
    /**
     * 身份证
     */
    public String identityCard;

    /**
     * 是否在验证系统中
     */
    public Boolean exists;

    /**
     * 是否进行了实名认证
     */
    public Boolean isVerified;

    /**
     * 验证身份证失败信息
     */
    public String verifyIdCardFailedMessage;

    /**
     * 验证身份证失败信息
     */
    public String verifyIdCardImgFailedMessage;

    @Override
    public String toString() {
        return "VerifyUserStatus{" +
                "identityCard='" + identityCard + '\'' +
                ", exists=" + exists +
                ", isVerified=" + isVerified +
                ", verifyIdCardFailedMessage=" + verifyIdCardFailedMessage +
                ", verifyIdCardImgFailedMessage=" + verifyIdCardImgFailedMessage +
                '}';
    }
}
