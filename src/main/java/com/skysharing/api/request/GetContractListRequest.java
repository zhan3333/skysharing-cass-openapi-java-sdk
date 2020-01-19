package com.skysharing.api.request;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetContractListResponse;

public class GetContractListRequest extends CassPayRequest<GetContractListResponse> {
    public String method = "Vzhuo.ContractList.Get";

    public String getMethod() {
        return this.method;
    }

    /**
     * 设置查询每页的数量
     *
     * @param size 每页的数量
     * @return 链式调用
     */
    public GetContractListRequest setSize(int size) {
        this.bizMap.put("size", size);
        return this;
    }

    /**
     * 设置查询所在的页数
     *
     * @param page 所在的页数
     * @return 链式调用
     */
    public GetContractListRequest setPage(int page) {
        this.bizMap.put("page", page);
        return this;
    }

    @Override
    public GetContractListResponse makeResponse(JSONObject response) {
        return new GetContractListResponse(response);
    }
}
