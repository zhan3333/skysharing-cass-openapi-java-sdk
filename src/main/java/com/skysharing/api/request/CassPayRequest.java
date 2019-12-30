package com.skysharing.api.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Signer;
import com.skysharing.api.exception.ResponseNotValidException;
import com.skysharing.api.response.CassPayResponse;
import okhttp3.*;

import java.io.IOException;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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

    public T send() throws ResponseNotValidException {
        this.datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try {
            JSONObject params = this.buildParams();
            String queryStr = signer.httpBuildQuery(JSON.toJavaObject(params, Map.class));
            JSONObject response = this.post(queryStr);
            String responseKey = this.getResponseKeyName();
            if (!response.containsKey(responseKey)) {
                throw new ResponseNotValidException("响应中必须要有" + responseKey + "键");
            }
            if (!response.containsKey("sign")) {
                throw new ResponseNotValidException("响应中必须要有sign键");
            }
            T cassResponse = this.makeResponse(response.getJSONObject(responseKey));
            cassResponse.sign = response.getString("sign");
            cassResponse.request = this;
            return cassResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseNotValidException(e.getMessage());
        }
    }

    public T makeResponse(JSONObject response) throws ResponseNotValidException {
        return (T) new CassPayResponse(response);
    }

    private JSONObject post(String queryStr) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(queryStr, MediaType.get("application/html; charset=utf-8"));
        Request request = new Request.Builder()
                .url(this.url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string());
        }
    }

    public String getMethod() {
        return this.method;
    }

    public String getResponseKeyName() {
        return this.getMethod().replace(".", "") + "Response";
    }

    public JSONObject buildParams() throws Exception {
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
}