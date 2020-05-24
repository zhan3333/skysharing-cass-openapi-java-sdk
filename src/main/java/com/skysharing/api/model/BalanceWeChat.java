package com.skysharing.api.model;

public class BalanceWeChat {
    /**
     * 账户总余额
     */
    public String childFAbalance = "";
    /**
     * 账户锁定余额
     */
    public String lockedAmt = "";
    /**
     * 可用余额(总余额-锁定余额)
     */
    public String canUseAmt = "";

    @Override
    public String toString() {
        return "BalanceWeChat{" +
                "childFAbalance='" + childFAbalance + '\'' +
                ", lockedAmt='" + lockedAmt + '\'' +
                ", canUseAmt='" + canUseAmt + '\'' +
                '}';
    }
}
