package com.skysharing.api.model;

/**
 * <p>BalanceAliPay class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class BalanceAliPay {
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "BalanceAliPay{" +
                "childFAbalance='" + childFAbalance + '\'' +
                ", lockedAmt='" + lockedAmt + '\'' +
                ", canUseAmt='" + canUseAmt + '\'' +
                '}';
    }
}
