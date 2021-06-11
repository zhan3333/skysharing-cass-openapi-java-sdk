package com.skysharing.api.model;

/**
 * <p>BalanceBank class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class BalanceBank {
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Bank{" +
                ", lockedAmt='" + lockedAmt + '\'' +
                ", canUseAmt='" + canUseAmt + '\'' +
                ", childFAbalance='" + childFAbalance + '\'' +
                '}';
    }
}
