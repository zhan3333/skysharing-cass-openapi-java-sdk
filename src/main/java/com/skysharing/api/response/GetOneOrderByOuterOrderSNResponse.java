package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetOneOrderByOuterOrderSNRequest;

/**
 * <p>GetOneOrderByOuterOrderSNResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetOneOrderByOuterOrderSNResponse extends CassPayResponse<GetOneOrderByOuterOrderSNRequest> {
    /**
     * 批次 UUID
     */
    public String rbUUID;
    /**
     * 订单 UUID
     */
    public String orderUUID;
    /**
     * 外部订单号
     */
    public String orderSN;
    /**
     * 批次状态，详情见（获取单个批次状态）
     */
    public Integer remitStatus;
    /**
     * 订单付款状态，详情见（获取单个批次状态）
     */
    public Integer orderStatus;
    /**
     * 到账时间
     */
    public String reachAt;
    /**
     * 第三方接口返回
     */
    public String responseMsg;

    /**
     * <p>Constructor for GetOneOrderByOuterOrderSNResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public GetOneOrderByOuterOrderSNResponse(JSONObject response) {
        super(response);
        this.rbUUID = this.content.getString("rbUUID");
        this.orderUUID = this.content.getString("orderUUID");
        this.orderSN = this.content.getString("orderSN");
        this.remitStatus = this.content.getInteger("remitStatus");
        this.orderStatus = this.content.getInteger("orderStatus");
        this.reachAt = this.content.getString("reachAt");
        this.responseMsg = this.content.getString("responseMsg");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "GetOneOrderByOuterOrderSNResponse{" +
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
