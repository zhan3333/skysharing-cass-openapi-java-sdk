package com.skysharing.api.request;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.response.GetUsersVerifyStatusResponse;

import java.util.List;

/**
 * <p>GetUsersVerifyStatusRequest class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetUsersVerifyStatusRequest extends CassPayRequest<GetUsersVerifyStatusResponse> {
    /**
     *
     */
    public static class Item {
        /**
         *
         */
        public String identityCard;
        /**
         *
         */
        public String receiptFANO;
        /**
         *
         */
        public Number receiptType;

        /**
         * @param identityCard 身份证号
         * @param receiptFANO 收款账号
         * @param receiptType 收款账号类型
         */
        public Item(String identityCard, String receiptFANO, Number receiptType) {
            this.identityCard = identityCard;
            this.receiptFANO = receiptFANO;
            this.receiptType = receiptType;
        }
    }

    public String method = "Vzhuo.UsersVerifyStatus.Get";

    /**
     * 设置要查询的身份证号码数组
     *
     * @param identityCards 设置要查询的身份证号码数组
     */
    public void setIdentityCards(List<String> identityCards) {
        this.bizMap.put("identityCards", identityCards);
    }

    /**
     * <p>addItem.</p>
     *
     * @param i a {@link com.skysharing.api.request.GetUsersVerifyStatusRequest.Item} object.
     * @return a {@link com.skysharing.api.request.GetUsersVerifyStatusRequest} object.
     */
    public GetUsersVerifyStatusRequest addItem(Item i) {
        JSONArray arr = this.bizMap.getJSONArray("items");
        if (arr == null) {
            arr = new JSONArray();
        }
        arr.add(i);
        this.bizMap.put("items", arr);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public GetUsersVerifyStatusResponse makeResponse(JSONObject response) {
        return new GetUsersVerifyStatusResponse(response);
    }

    /**
     * <p>Getter for the field <code>method</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMethod() {
        return this.method;
    }
}

