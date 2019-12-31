package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetOneOrderStatusRequest;

public class GetOneOrderStatusResponse extends CassPayResponse<GetOneOrderStatusRequest> {
    public String rbUUID;
    public String orderUUID;
    public String orderSN;
    /**
     * 批次状态
     */
    public Integer remitStatus;
    /**
     * 订单付款状态
     */
    public Integer orderStatus;
    /**
     * 到账时间
     */
    public String reachAt;
    public String responseMsg;

    public GetOneOrderStatusResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
        this.orderUUID = this.content.getString("orderUUID");
        this.orderSN = this.content.getString("orderSN");
        this.remitStatus = this.content.getInteger("remitStatus");
        this.orderStatus = this.content.getInteger("orderStatus");
        this.reachAt = this.content.getString("reachAt");
        this.responseMsg = this.content.getString("responseMsg");
    }

    @Override
    public String toString() {
        return "GetOneOrderStatusResponse{" +
                "rbUUID='" + rbUUID + '\'' +
                ", orderUUID='" + orderUUID + '\'' +
                ", orderSN='" + orderSN + '\'' +
                ", remitStatus=" + remitStatus +
                ", orderStatus=" + orderStatus +
                ", reachAt='" + reachAt + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}
