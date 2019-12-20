package com.skysharing.api.model;

public class BankPayOrder {
    /**
     * 商户订单号
     * required
     */
    public String orderSN;

    /**
     * 收款人金融账号，例如银行账号，支付宝账号，微 信账号
     * required
     */
    public String receiptFANO;

    /**
     * 收款人户名(真实姓名)
     * required
     */
    public String payeeAccount;

    /**
     * 收款方开户行名称(总行即可)
     */
    public String receiptBankName;

    /**
     * 收款方开户行地(省会或者直辖市即可)
     */
    public String receiptBankAddr;

    /**
     * 收方联行号
     */
    public String CRCHGNO;

    /**
     * 预期打款金额
     * required
     */
    public String requestPayAmount;

    /**
     * name(合同名称)和 description(合同描述)
     */
    public PayOrderData data = new PayOrderData();

    public BankPayOrder (String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
    }

    public BankPayOrder (String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount, String receiptBankName, String receiptBankAddr, String CRCHGNO) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        this.receiptBankName = receiptBankName;
        this.receiptBankAddr = receiptBankAddr;
        this.CRCHGNO = CRCHGNO;
    }
}
