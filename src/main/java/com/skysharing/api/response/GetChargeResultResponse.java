package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetBalanceRequest;

public class GetChargeResultResponse extends CassPayResponse<GetBalanceRequest> {
    public String rechargeSBSN;
    public String status;
    public String applyAmount;
    public String realAmount;

    public GetChargeResultResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
        this.status = this.content.getString("status");
        this.applyAmount = this.content.getString("applyAmount");
        this.realAmount = this.content.getString("realAmount");
    }

    @Override
    public String toString() {
        return "GetChargeResultResponse{" +
                "rechargeSBSN='" + rechargeSBSN + '\'' +
                ", status='" + status + '\'' +
                ", applyAmount='" + applyAmount + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
