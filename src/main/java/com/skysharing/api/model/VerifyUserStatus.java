package com.skysharing.api.model;

/**
 * <p>VerifyUserStatus class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
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

    /**
     * 验证状态, exists=false 时为空 ''
     * exists=true 时, 包含以下结果
     * pending: 验证中
     * failed: 验证失败
     * success: 验证成功
     */
    public String status;

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "VerifyUserStatus{" +
                "identityCard='" + identityCard + '\'' +
                ", exists=" + exists +
                ", isVerified=" + isVerified +
                ", verifyIdCardFailedMessage=" + verifyIdCardFailedMessage +
                ", verifyIdCardImgFailedMessage=" + verifyIdCardImgFailedMessage +
                ", status=" + status +
                '}';
    }
}
