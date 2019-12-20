package com.skysharing.api.model;

public class BalanceBank {
    /**
     * 银行账号
     */
    public String bankAccout = "";
    /**
     * 锁定金额
     */
    public String lockedAmt = "";
    /**
     * 可用余额(总余额-锁定余额)
     */
    public String canUseAmt = "";
    /**
     * 账户总余额
     */
    public String childFAbalance = "";

    public String mandatoryName = "";

    @Override
    public String toString() {
        return "Bank{" +
                "bankAccout='" + bankAccout + '\'' +
                ", lockedAmt='" + lockedAmt + '\'' +
                ", canUseAmt='" + canUseAmt + '\'' +
                ", childFAbalance='" + childFAbalance + '\'' +
                ", mandatoryName='" + mandatoryName + '\'' +
                '}';
    }
}
