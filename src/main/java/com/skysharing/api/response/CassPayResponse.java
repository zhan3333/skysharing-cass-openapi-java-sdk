package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Signer;
import com.skysharing.api.request.CassPayRequest;

import java.security.PublicKey;

public class CassPayResponse<T extends CassPayRequest> {

    /**
     * 主错误码
     */
    public String code;

    /**
     * 主消息
     */
    public String message;

    /**
     * 子错误码
     */
    public String subCode;

    /**
     * 子消息
     */
    public String subMsg;

    /**
     * 响应的业务处理结果
     */
    public JSONObject content;

    /**
     * 响应中的签名
     */
    public String sign;

    /**
     * 请求对象, 基类为 CassPayRequest
     */
    public T request;

    /**
     * 原始响应体中的 *Response key 对应的值部分
     */
    public JSONObject raw;

    /**
     * 验证响应结果的vzhuo公钥
     */
    public PublicKey vzhuoPublicKey;

    public CassPayResponse(JSONObject response) {
        this.raw = response;
        this.code = response.getString("code");
        this.subCode = response.getString("subCode");
        this.subMsg = response.getString("subMsg");
        this.message = response.getString("message");
        System.out.println(this.raw);
        this.content = response.getJSONObject("content");
    }

    public boolean verify() throws Exception {
        Signer signer = new Signer();
        return signer.verifyParams(this.raw, this.vzhuoPublicKey, this.sign);
    }

    @Override
    public String toString() {
        return "CassPayResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }

    /**
     * @return 返回这个请求的调用是否发生了错误 (当 code == "10000") 时, 可以理解为业务成功
     */
    public Boolean isSuccess() {
        return this.isRequestSuccess() && this.code.equals("10000");
    }

    /**
     * @return 返回是否得到了正确的响应 (是指是否得到了需要的响应结果, 无论 code 为多少)
     */
    public Boolean isRequestSuccess() {
        if (this.sign == null || this.sign.equals("")) {
            return false;
        }
        return this.code != null && !this.code.equals("");
    }
}
