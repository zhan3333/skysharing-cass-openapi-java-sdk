package com.skysharing.api;

import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.exception.ResponseNotValidException;
import com.skysharing.api.request.CassPayRequest;
import com.skysharing.api.response.CassPayResponse;

import java.security.PrivateKey;
import java.security.PublicKey;

public class DefaultCassPayClient {
    public static final Signer signer = new Signer();
    private final String url;
    private final String appId;
    private final PrivateKey appPrivateKey;
    private final PublicKey cassPublicKey;
    private final String format;
    private final String signType;

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey, String format, String signType) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = format;
        this.signType = signType;
    }

    public DefaultCassPayClient(String url, String appId, String appPrivateKey, String cassPublicKey) throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.url = url;
        this.appId = appId;
        this.appPrivateKey = signer.getPrivateKey(appPrivateKey);
        this.cassPublicKey = signer.getPublicKey(cassPublicKey);
        this.format = "JSON";
        this.signType = "RSA2";
    }

    public <T extends CassPayRequest, F extends CassPayResponse> F execute(T request) throws ResponseNotValidException {
        request.url = this.url;
        request.APPID = this.appId;
        request.format = this.format;
        request.signType = this.signType;
        request.privateKey = this.appPrivateKey;
        F response = (F) request.send();
        response.vzhuoPublicKey = this.cassPublicKey;
        return response;
    }
}
