package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetBalanceRequest;

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

    public GetChannelDataResponse(JSONObject response) {
        super(response);
        this.accountName = this.content.getString("accountName");
        this.bankAccount = this.content.getString("bankAccount");
        this.bankCardNO = this.content.getString("bankCardNO");
        this.childExclusiveAccount = this.content.getString("childExclusiveAccount");
    }

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
