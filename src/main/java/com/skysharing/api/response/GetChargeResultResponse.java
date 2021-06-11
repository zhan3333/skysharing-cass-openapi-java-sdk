package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetBalanceRequest;

/**
 * <p>GetChargeResultResponse class.</p>
 *
 * @deprecated v2.4.3 停用
 * @author zhan
 * @version $Id: $Id
 */
public class GetChargeResultResponse extends CassPayResponse<GetBalanceRequest> {
    /**
     *
     */
    public String rechargeSBSN;
    /**
     *
     */
    public String status;
    /**
     *
     */
    public String applyAmount;
    /**
     *
     */
    public String realAmount;

    /**
     * <p>Constructor for GetChargeResultResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public GetChargeResultResponse(JSONObject response) {
        super(response);
        this.rechargeSBSN = this.content.getString("rechargeSBSN");
        this.status = this.content.getString("status");
        this.applyAmount = this.content.getString("applyAmount");
        this.realAmount = this.content.getString("realAmount");
    }

    /** {@inheritDoc} */
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
