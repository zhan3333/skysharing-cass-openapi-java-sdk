package com.skysharing.api.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.model.ContractListData;
import com.skysharing.api.request.GetContractListRequest;

import java.util.List;

public class GetContractListResponse extends CassPayResponse<GetContractListRequest> {

    /**
     * 总数
     */
    public int total = 0;

    /**
     * 返回的数据
     */
    public List<ContractListData> data;

    public GetContractListResponse(JSONObject response) {
        super(response);
        if (!this.content.toJSONString().equals("") && !this.content.toJSONString().equals("{}")) {
            this.total = this.content.getInteger("total");
            this.data = JSON.parseArray(this.content.getString("data"), ContractListData.class);
        }
    }

    @Override
    public String toString() {
        return "GetContractListResponse{" +
                "total=" + total +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                '}';
    }
}