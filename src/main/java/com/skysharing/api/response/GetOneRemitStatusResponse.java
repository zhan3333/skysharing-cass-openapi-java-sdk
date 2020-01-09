package com.skysharing.api.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.RemitOrder;
import com.skysharing.api.request.GetOneRemitStatusRequest;

import java.util.List;

public class GetOneRemitStatusResponse extends CassPayResponse<GetOneRemitStatusRequest> {
    public Integer status;
    public String responseMsg;
    public String rbUUID;
    public String totalExpectAmount;
    public String SBSNCN;
    public String totalRealPayAmount;
    public String totalServiceCharge;
    public String subStatus;
    public List<RemitOrder> remitOrders;

    public GetOneRemitStatusResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
        this.responseMsg = this.content.getString("responseMsg");
        this.status = this.content.getInteger("status");
        this.totalExpectAmount = this.content.getString("totalExpectAmount");
        this.SBSNCN = this.content.getString("SBSNCN");
        this.totalRealPayAmount = this.content.getString("totalRealPayAmount");
        this.totalServiceCharge = this.content.getString("totalServiceCharge");
        this.subStatus = this.content.getString("subStatus");
        this.remitOrders = JSON.parseArray(this.content.getString("orderData"), RemitOrder.class);
    }

    @Override
    public String toString() {
        return "GetOneRemitStatusResponse{" +
                "status=" + status +
                ", responseMsg='" + responseMsg + '\'' +
                ", rbUUID='" + rbUUID + '\'' +
                ", totalExpectAmount='" + totalExpectAmount + '\'' +
                ", SBSNCN='" + SBSNCN + '\'' +
                ", totalRealPayAmount='" + totalRealPayAmount + '\'' +
                ", totalServiceCharge='" + totalServiceCharge + '\'' +
                ", subStatus='" + subStatus + '\'' +
                ", remitOrders=" + remitOrders +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
