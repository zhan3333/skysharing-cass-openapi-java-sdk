package com.skysharing.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skysharing.api.exception.*;
import com.skysharing.api.request.CassPayRequest;
import com.skysharing.api.response.CassPayResponse;
import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.skysharing.api.Signer.SIGNATURE_ALGORITHM;

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
    private Boolean debug = false;

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey, String format, String signType) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = format;
        this.signType = signType;
        this.setTimeout(TimeUnit.SECONDS, 60, 60, 60, 60);
    }

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = "JSON";
        this.signType = "RSA2";
        this.setTimeout(TimeUnit.SECONDS, 60, 60, 60, 60);
    }

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey, OkHttpClient c) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = "JSON";
        this.signType = "RSA2";
        this.setOkHttp(c);
    }

    /**
     * 设置 Http 请求客户端对象
     *
     * @param c OkHttp 对象
     * @return this
     */
    public DefaultCassPayClient setOkHttp(OkHttpClient c) {
        this.client = c;
        return this;
    }


    /**
     * 设置默认 Http 请求客户端对象
     *
     * @return this
     */
    public DefaultCassPayClient setOkHttp() {
        this.client = new OkHttpClient();
        return this;
    }

    /**
     * 设置指定超时时间的 Http 请求客户端对象
     *
     * @param unit           时间单位
     * @param connectTimeout 连接超时时间
     * @param writeTimeout   写超时
     * @param readTimeout    读超时
     * @return this
     */
    public DefaultCassPayClient setTimeout(TimeUnit unit, Integer connectTimeout, Integer writeTimeout, Integer readTimeout) {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, unit)
                .writeTimeout(writeTimeout, unit)
                .readTimeout(readTimeout, unit)
                .build();
        return this;
    }

    public DefaultCassPayClient setTimeout(TimeUnit unit, Integer connectTimeout, Integer writeTimeout, Integer readTimeout, Integer callTimeout) {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, unit)
                .writeTimeout(writeTimeout, unit)
                .readTimeout(readTimeout, unit)
                .callTimeout(callTimeout, unit)
                .build();
        return this;
    }

    /**
     * 开启 debug 将会通过 System.out.print 输出日志
     *
     * @param debug 是否开启
     * @return this
     */
    public DefaultCassPayClient setDebug(Boolean debug) {
        this.debug = debug;
        return this;
    }

    public <T extends CassPayRequest, F extends CassPayResponse> F execute(T request) throws SignException, RequestFailedException, ResponseNotValidException, RequestTimeoutException {
        request.url = this.url;
        request.APPID = this.appId;
        request.format = this.format;
        request.signType = this.signType;
        request.privateKey = this.appPrivateKey;
        request.datetime = LocalDateTime.now().format(this.datetimeFormat);
        JSONObject params = request.buildParams();
        if (debug) {
            System.out.println("Request: " + request.toString());
            System.out.println("Request JSON: " + params);
        }
        String queryStr = signer.httpBuildQuery(JSON.toJavaObject(params, Map.class));
        JSONObject response = this.post(queryStr);
        if (debug) {
            System.out.println("Response Body: " + response.toString());
        }
        String responseKey = request.getResponseKeyName();
        if (!response.containsKey(responseKey)) {
            throw new ResponseNotValidException("响应中必须要有" + responseKey + "键");
        }
        if (!response.containsKey("sign")) {
            throw new ResponseNotValidException("响应中必须要有sign键");
        }
        if (debug) {
            System.out.println("Response JSON: " + response.getJSONObject(responseKey));
        }
        F cassResponse = (F) request.makeResponse(response.getJSONObject(responseKey));
        cassResponse.sign = response.getString("sign");
        cassResponse.request = request;
        cassResponse.vzhuoPublicKey = this.cassPublicKey;
        return cassResponse;
    }

    private JSONObject post(String queryStr) throws RequestFailedException, RequestTimeoutException {
        RequestBody reqBody = RequestBody.create(queryStr, MediaType.get("application/html; charset=utf-8"));
        Request request = new Request.Builder()
                .url(this.url)
                .post(reqBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            switch (response.code()) {
                case 200:
                    break;
                case 502:
                    throw new RequestFailedException("502 Bad Gateway");
                case 504:
                    throw new SocketTimeoutException("504");
                default:
                    throw new RequestFailedException("code " + response.code());
            }
            String body = Objects.requireNonNull(response.body()).string();
            return JSON.parseObject(body);
        } catch (NullPointerException e) {
            throw new RequestFailedException("响应 body 为空");
        } catch (SocketTimeoutException e) {
            throw new RequestTimeoutException("请求超时: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RequestFailedException("请求失败: " + e.getMessage());
        }
    }

    /**
     * 验证通知的签名
     *
     * @param body 通知请求体
     * @return 是否验证通过
     */
    public boolean verifyNotify(String body) {
        if (this.debug) {
            System.out.printf("Verify Notify Str: %s \n", body);
        }
        if (body.isEmpty()) {
            return false;
        }
        JSONObject json = JSON.parseObject(body);
        String sign = json.getString("sign");
        JSONObject req = json.getJSONObject("response");
        // 去除空值
        JSONObject filteredParams = new JSONObject();
        for (String key : req.keySet()) {
            if (req.getString(key).equals("") || req.getString(key).equals("{}") || req.getString(key).equals("[]")) {
                break;
            }
            filteredParams.put(key, req.get(key));
        }
        // 排序 + json encode
        String waitSignStr = JSON.toJSONString(filteredParams, SerializerFeature.MapSortField, SerializerFeature.WriteSlashAsSpecial);
        // 替换空字符串
        waitSignStr = waitSignStr.replace(" ", "");
        // url encode
        try {
            waitSignStr = URLEncoder.encode(waitSignStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        if (this.debug) {
            System.out.printf("Verify Notify Wait Sign Str: %s \n", waitSignStr);
        }
        try {
            // 创建验证对象
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(this.cassPublicKey);
            signature.update(waitSignStr.getBytes(StandardCharsets.UTF_8));
            // 执行验证
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            return false;
        }
    }
}
