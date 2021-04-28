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
     * 身份证号, 在商户被要求实名认证时,该字段必填
     */
    public String identityCard = "";

    /**
     * name(合同名称)和 description(合同描述)
     */
    public PayOrderData data = new PayOrderData();

    /**
     * 异步通知地址： http://xxx.xxx.cn/xx/asynNotify.htm
     */
    public String notifyUrl = "";

    /**
     * 个税(商户类型为劳务外包模式必填,值为大 于或等于 0 的数字)
     * 例如：2.00
     */
    public String tax = null;

    public BankPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
    }

    /**
     *
     * 劳务模式下加个税
     * @param orderSN
     * @param receiptFANO
     * @param payeeAccount
     * @param requestPayAmount
     * @param tax
     */
    public BankPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount, String tax) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        this.tax = tax;
    }


    public BankPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount, String receiptBankName, String receiptBankAddr, String CRCHGNO) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        if (!receiptBankName.equals("")) {
            this.receiptBankName = receiptBankName;
        }
        if (!receiptBankAddr.equals("")) {
            this.receiptBankAddr = receiptBankAddr;
        }
        if (!CRCHGNO.equals("")) {
            this.CRCHGNO = CRCHGNO;
        }
    }

    public BankPayOrder(String orderSN, String receiptFANO, String payeeAccount, String requestPayAmount, String receiptBankName, String receiptBankAddr, String CRCHGNO, PayOrderData payOrderData) {
        this.orderSN = orderSN;
        this.receiptFANO = receiptFANO;
        this.payeeAccount = payeeAccount;
        this.requestPayAmount = requestPayAmount;
        if (!receiptBankName.equals("")) {
            this.receiptBankName = receiptBankName;
        }
        if (!receiptBankAddr.equals("")) {
            this.receiptBankAddr = receiptBankAddr;
        }
        if (!CRCHGNO.equals("")) {
            this.CRCHGNO = CRCHGNO;
        }
        this.data = payOrderData;
    }

    /**
     * 设置订单身份证号
     *
     * @param identityCard 身份证号
     * @return 原对象
     */
    public BankPayOrder setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    /**
     * 设置回调地址
     *
     * @param notifyUrl 接收通知的地址
     * @return 当前对象
     */
    public BankPayOrder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    /**
     * 设置订单个税
     *
     * @param tax 个税
     * @return this
     */
    public BankPayOrder setTax(String tax) {
        this.tax = tax;
        return this;
    }
}
