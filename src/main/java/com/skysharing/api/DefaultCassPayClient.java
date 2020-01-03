package com.skysharing.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.exception.*;
import com.skysharing.api.request.CassPayRequest;
import com.skysharing.api.response.CassPayResponse;
import okhttp3.*;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DefaultCassPayClient {
    public static final Signer signer = new Signer();
    private final String url;
    private final String appId;
    private final PrivateKey appPrivateKey;
    private final PublicKey cassPublicKey;
    private final String format;
    private final String signType;
    private OkHttpClient client;
    private DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey, String format, String signType) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = format;
        this.signType = signType;
        this.setTimeout(TimeUnit.SECONDS, 30, 30, 30);
    }

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = "JSON";
        this.signType = "RSA2";
        this.setTimeout(TimeUnit.SECONDS, 30, 30, 30);
    }

    public DefaultCassPayClient setTimeout(TimeUnit unit, Integer connectTimeout, Integer writeTimeout, Integer readTimeout) {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, unit)
                .writeTimeout(writeTimeout, unit)
                .readTimeout(readTimeout, unit)
                .build();
        return this;
    }

    public <T extends CassPayRequest, F extends CassPayResponse> F execute(T request) throws SignException, RequestFailedException, ResponseNotValidException {
        request.url = this.url;
        request.APPID = this.appId;
        request.format = this.format;
        request.signType = this.signType;
        request.privateKey = this.appPrivateKey;
        request.datetime = LocalDateTime.now().format(this.datetimeFormat);
        JSONObject params = request.buildParams();
        String queryStr = signer.httpBuildQuery(JSON.toJavaObject(params, Map.class));
        JSONObject response = this.post(queryStr);
        String responseKey = request.getResponseKeyName();
        if (!response.containsKey(responseKey)) {
            throw new ResponseNotValidException("响应中必须要有" + responseKey + "键");
        }
        if (!response.containsKey("sign")) {
            throw new ResponseNotValidException("响应中必须要有sign键");
        }
        F cassResponse = (F) request.makeResponse(response.getJSONObject(responseKey));
        cassResponse.sign = response.getString("sign");
        cassResponse.request = request;
        cassResponse.vzhuoPublicKey = this.cassPublicKey;
        return cassResponse;
    }

    private JSONObject post(String queryStr) throws RequestFailedException {
        RequestBody body = RequestBody.create(queryStr, MediaType.get("application/html; charset=utf-8"));
        Request request = new Request.Builder()
                .url(this.url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RequestFailedException("请求失败: " + e.getMessage());
        }
    }
}
