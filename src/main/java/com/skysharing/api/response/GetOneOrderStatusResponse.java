package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetOneOrderStatusRequest;

public class GetOneOrderStatusResponse extends CassPayResponse<GetOneOrderStatusRequest> {
    public String rbUUID;
    public String orderUUID;
    public String orderSN;
    public Integer remitStatus;
    public String remitAt;
    public String responseMsg;

    public GetOneOrderStatusResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
        this.orderUUID = this.content.getString("orderUUID");
        this.orderSN = this.content.getString("orderSN");
        this.remitStatus = this.content.getInteger("remitStatus");
        this.remitAt = this.content.getString("remitAt");
        this.responseMsg = this.content.getString("responseMsg");
    }

    @Override
    public String toString() {
        return "GetOneOrderStatusResponse{" +
                "rbUUID='" + rbUUID + '\'' +
                ", orderUUID='" + orderUUID + '\'' +
                ", orderSN='" + orderSN + '\'' +
                ", remitStatus=" + remitStatus +
                ", remitAt='" + remitAt + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
