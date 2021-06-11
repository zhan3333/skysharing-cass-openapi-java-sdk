package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetBalanceRequest;

/**
 * <p>GetChannelDataResponse class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class GetChannelDataResponse extends CassPayResponse<GetBalanceRequest> {
    /**
     * 账户名称或开户名称
     */
    public String accountName;
    /**
     * 开户银行
     */
    public String bankAccount;
    /**
     * 开户银行卡号
     */
    public String bankCardNO;
    /**
     * 子账户专属ID, 可以为null
     */
    public String childExclusiveAccount;

    /**
     * <p>Constructor for GetChannelDataResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public GetChannelDataResponse(JSONObject response) {
        super(response);
        this.accountName = this.content.getString("accountName");
        this.bankAccount = this.content.getString("bankAccount");
        this.bankCardNO = this.content.getString("bankCardNO");
        this.childExclusiveAccount = this.content.getString("childExclusiveAccount");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "GetChannelDataResponse{" +
                "accountName='" + accountName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankCardNO='" + bankCardNO + '\'' +
                ", childExclusiveAccount='" + childExclusiveAccount + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
