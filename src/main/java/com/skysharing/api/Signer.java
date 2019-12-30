package com.skysharing.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;


public class Signer {
    public final static String ALGORITHM = "RSA";
    public final static String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /**
     * 从公钥字符串中获取公钥对象, 公钥不需要 pkcs8 格式, 需要去掉公钥字符串的头尾及换行符
     * @param key 公钥字符串
     * @return 公钥对象
     * @throws InvalidPublicKeyException 无效的公钥字符串
     */
    public PublicKey getPublicKey(String key) throws InvalidPublicKeyException {

        try {
            byte[] keyBytes = (Base64.getDecoder().decode(key));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            throw new InvalidPublicKeyException(e.getMessage());
        }
    }

    /**
     * 私钥生成 `openssl genrsa -out rsa_private_key.pem 2048`
     * 公钥生成 `openssl rsa -in rsa_private_key.pem -pubout -out rsa_public_key.pem`
     * 私钥pkcs8通过 `openssl pkcs8 -topk8 -nocrypt -inform PEM -in rsa_private_key.pem -out rsa_private_key_pkcs8.pem` 命令来产生, 可以去除头尾及换行信息
     * @param key 私钥字符串, 可以是去掉了头尾信息及换行符的字符串
     * @return 返回私钥对象
     * @throws InvalidPrivateKeyException 无效的私钥字符串
     */
    public PrivateKey getPrivateKey(String key) throws InvalidPrivateKeyException {
        try {
            byte[] keyBytes = (Base64.getDecoder().decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        } catch (Exception e) {
            throw new InvalidPrivateKeyException(e.getMessage());
        }
    }

    public String sign(String str, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 对Map进行签名, 包含排序|url_encode|等步骤
     * @param params 待签名的Map
     * @param privateKey 私钥
     * @return 返回签名字符串
     * @throws Exception 签名失败
     */
    public String singParams(JSONObject params, PrivateKey privateKey) throws Exception {
        String waitSignStr = this.paramsToWaitSignStr(params);
        return this.sign(waitSignStr, privateKey);
    }

    /**
     * 对Map进行验签
     * @param params 待验签Map
     * @param publicKey 公钥对象
     * @param sign 签名字符串
     * @return 判断数据和签名是否正确
     * @throws Exception 异常
     */
    public Boolean verifyParams(JSONObject params, PublicKey publicKey, String sign) throws Exception {
        String str = this.paramsToWaitVerifyStr(params);
        return this.verify(str, publicKey, sign);
    }

    public Boolean verify(String str, PublicKey publicKey, String sign) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    public String paramsToWaitSignStr(JSONObject params) throws UnsupportedEncodingException {
        String newStr = params.toJSONString(params, SerializerFeature.SortField.MapSortField);
        newStr = newStr.replace(" ", "");
        newStr = newStr.replace("/", "\\/");
        newStr = URLEncoder.encode(newStr, "UTF-8");
        newStr = newStr.replace("*", "%2A");
        System.out.println(newStr);
        return newStr;
    }

    public String paramsToWaitVerifyStr(JSONObject params) throws UnsupportedEncodingException {
        String newStr = params.toJSONString(params, SerializerFeature.SortField.MapSortField);
        newStr = URLEncoder.encode(newStr, "UTF-8");
        return newStr;
    }

    public String httpBuildQuery(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key + "=");
                if (map.get(key).equals("")) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
                    value = URLEncoder.encode(value, "UTF-8");
                    sb.append(value + "&");
                }
            }
        }
        return sb.toString();
    }
}
