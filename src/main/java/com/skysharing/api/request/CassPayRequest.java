package com.skysharing.api.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Signer;
import com.skysharing.api.exception.SignException;
import com.skysharing.api.response.CassPayResponse;

import java.security.PrivateKey;

public class CassPayRequest<T extends CassPayResponse> {
    public String url = "https://fuwu-openapi.skysharing.cn/gateway/cass";
    public JSONObject bizMap = new JSONObject();
    public String method = "";
    public String APPID = "";
    public String format = "JSON";
    public String charset = "UTF-8";
    public String signType = "RSA2";
    public String sign = "";

    /**
     * format: Y-m-d H:i:s
     */
    public String datetime = "";
    public String version = "1.0";
    public String isItEncrypted = "0";
    public PrivateKey privateKey;
    private Signer signer = new Signer();

    public T makeResponse(JSONObject response) {
        return (T) new CassPayResponse(response);
    }

    public String getMethod() {
        return this.method;
    }

    public String getResponseKeyName() {
        return this.getMethod().replace(".", "") + "Response";
    }

    public JSONObject buildParams() throws SignException {
        JSONObject params = new JSONObject();
        params.put("method", this.getMethod());
        params.put("APPID", this.APPID);
        params.put("format", this.format);
        params.put("charset", this.charset);
        params.put("datetime", this.datetime);
        params.put("version", this.version);
        params.put("signType", this.signType);
        params.put("bizParam", JSON.toJSONString(this.bizMap));
        this.sign = signer.singParams(params, this.privateKey);
        params.put("sign", this.sign);
        return params;
    }

    @Override
    public String toString() {
        return "CassPayRequest{" +
                "url='" + url + '\'' +
                ", bizMap=" + bizMap +
                ", method='" + method + '\'' +
                ", APPID='" + APPID + '\'' +
                ", format='" + format + '\'' +
                ", charset='" + charset + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", datetime='" + datetime + '\'' +
                ", version='" + version + '\'' +
                ", isItEncrypted='" + isItEncrypted + '\'' +
                '}';
    }
}