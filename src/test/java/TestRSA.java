import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.Signer;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;


public class TestRSA {
    private String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5rT49txD03KYuktQaaSic0efuGm9wlkTqWK4HQxZW6Tv4mvvnJBXScq4BI6URNy+L3ghqZrIQvGxtsR/VGiOXnSLguVl8tBZFtqChUenZz6Ur9thjP8TRTiBjlarHsfbWWyW34ZsmLlqVGx15YGtigsW0KLGJLblowBM2/rHxZ4BlWiOwkuTUlBhYUd8cKRMzvFaFYj4uuqlUGr6MZK3hv7is6CPSOwIt2+SSvrwPhs+1vmaQKiT2YoXb5qmiiLe0C+wOPrVfDls4RC8miLCCd90X7Bnvoi6e1wH4dXZuG38xSHFEt8LNmRNMsG90wrq1zGKni47Ila+NsGIz4h8HQIDAQAB";
    private String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmtPj23EPTcpi6S1BppKJzR5+4ab3CWROpYrgdDFlbpO/ia++ckFdJyrgEjpRE3L4veCGpmshC8bG2xH9UaI5edIuC5WXy0FkW2oKFR6dnPpSv22GM/xNFOIGOVqsex9tZbJbfhmyYuWpUbHXlga2KCxbQosYktuWjAEzb+sfFngGVaI7CS5NSUGFhR3xwpEzO8VoViPi66qVQavoxkreG/uKzoI9I7Ai3b5JK+vA+Gz7W+ZpAqJPZihdvmqaKIt7QL7A4+tV8OWzhELyaIsIJ33RfsGe+iLp7XAfh1dm4bfzFIcUS3ws2ZE0ywb3TCurXMYqeLjsiVr42wYjPiHwdAgMBAAECggEAJqC0crjMjnHIiqCjlRygqoaagokJ4amCdkD2LL7tkz+ZfqKt2tv8EXnkt7absq/3FAGcOUaWM0c+hyh9XUeoVr5SwZbhK/eggwgRBHnL2KiMwqkLu0zWECf24Ts3qY6y9lUNKd3a/vNEj2AdmVDOGqfiqQon/Ou2pUUemVJy8m5BwYLafp5tZk/Yvo5ns48I6recTaQiCTbwXlD0hSYVHhaOhtzIXLk5hjBKGasDNuvAmhqZM+XGnITTq4Z+wGZS3Ir4ynGZMn4FzzTXd41SZ/tucKjFmh3tSeCQjTBzKS+Y6je694UNCKy0TTFc9mANFs4qL+m58uD6KWUiRx/27QKBgQD/FgJQPB2W6Yw92vTS1fJA1irJLPeUoy3R99qDAlGjbnwht31943/DfhBtBGdEOUaXskJvtZLYwU4aE9CytUKQeqhuIU1uRosE44V3wq6EFTknXGFQ+XAT9e+hAjSn7HcHYjPRvxMJvmg3N/86oQAq/Zt6UNGmvCRwQzSdmdeUmwKBgQDniJm7v56xVuO/5W+P9yiNUayHgCfya7/eQij0Rw9hIJ0mKEUl2ENGGdtw6ZqsdrSD6yHkzGLTGCd0cy9cifwz25AjnSOf5kLHJem9WuxqcFlZQlF8X+ba1OOJwfQxyCrjhR2/1QttVGOl84kMDij/YYA0Ip+EQ5mP5YWxsfzRpwKBgGHX3VdNV8Q1HRf0zoe4jM2V2J+F4glfdgnd2jD6SLo5fN+p9Da/dphT2JUYZUvj9FHnjFgw+3ys2Ppjs19462ljwwtLNtofsN9VLjK7uBjt0xhn9OQMaZMeeKX3pJ7sstNgNaQ0eGb6ZBsaHYoti1TyJhsKuQANlqSBAsOY6PoVAoGAfpjIRkoeASVbXj+bYoJn3+16gpRmQzz7KhHOxtJb7GWix4xZUroO/rOAsxpoAkjdpwvX+nxuxLF+UUPx741bIxe4lmCDbBjBBpcKWQjwH7rSf+WtHG5rkde5mkc8uEOUf9b3Tz60AtGTsteYZckQTaMIMHmF8xHrUyzSjzic3h0CgYEApidFokFSKY39OLk9WqWLd+XfL1ykOJh4xsREADo09oIvVtRrtaojaDpCg6/ewb5eVHzIxutTJqrKNIiO00+jK9OBqHf3NIbVA2aAxj2+zTZCc/+zkqDjnhWFcKLU4hAH5j6mQrDcn7hzE5ic/nyILQOyO2hQVXqM82RFcIJPCkE=";

    private Signer signer = new Signer();
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Before
    public void init() throws InvalidPublicKeyException, InvalidPrivateKeyException {
        this.publicKey = signer.getPublicKey(this.publicKeyStr);
        this.privateKey = signer.getPrivateKey(this.privateKeyStr);
    }

    @Test
    public void testGetPublicKey() throws Exception {
        PublicKey publicKey = signer.getPublicKey(this.publicKeyStr);
        Assert.assertEquals("X.509", publicKey.getFormat());
        Assert.assertEquals("RSA", publicKey.getAlgorithm());
    }

    @Test
    public void testGetPrivateKey() throws Exception {
        PrivateKey privateKey = signer.getPrivateKey(this.privateKeyStr);
        Assert.assertEquals("PKCS#8", privateKey.getFormat());
        Assert.assertEquals("RSA", privateKey.getAlgorithm());
    }

    @Test
    public void testSign() throws Exception {
        JSONObject bizParam = new JSONObject();
        bizParam.put("test", "test");
        JSONObject params = new JSONObject();
        params.put("method", "test");
        params.put("APPID", "123456");
        params.put("format", "JSON");
        params.put("charset", "UTF-8");
        params.put("datetime", "2019-12-19 06:00:00");
        params.put("version", "1.0");
        params.put("signType", "RSA2");
        params.put("bizParam", bizParam.toJSONString());
        String sign = signer.singParams(params, this.privateKey);
        System.out.println(sign);
        Boolean valid = signer.verifyParams(params, this.publicKey, sign);
        System.out.println(valid);
    }

    @Test
    public void testGetWaitSignStr() throws IOException {
        JSONObject bizParam = new JSONObject();
        bizParam.put("name", "詹光");
        JSONObject params = new JSONObject();
        params.put("method", "test");
        params.put("APPID", "123456");
        params.put("format", "JSON");
        params.put("charset", "UTF-8");
        params.put("datetime", "2019-12-19 06:00:00");
        params.put("version", "1.0");
        params.put("signType", "RSA2");
        params.put("bizParam", bizParam.toJSONString());
        String newStr = this.signer.paramsToWaitSignStr(params);
        System.out.println(newStr);
    }

    @Test
    public void testBuildHttpQuery() throws UnsupportedEncodingException {
        HashMap<String, String> map = new HashMap<>();
        JSONObject bizParam = new JSONObject();
        bizParam.put("test", "test");
        map.put("method", "test");
        map.put("APPID", "123456");
        map.put("format", "JSON");
        map.put("charset", "UTF-8");
        map.put("datetime", "2019-12-19 06:00:00");
        map.put("version", "1.0");
        map.put("signType", "RSA2");
        map.put("bizParam", bizParam.toJSONString());
        System.out.println(map);
        String queryStr = this.signer.httpBuildQuery(map);
        System.out.println(queryStr);
    }
}
