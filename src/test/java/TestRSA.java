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
    //    private String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoNmsSDcjFRG9VYeCtKG/+DquAa7gGIK15p575mQvU3vZ1Wru/Oc3qYIwU7kAGjwUn1ZnAsB8Kj+XsnQiRk7jaJ4he5tSxZYoryvaw2AjHDFA1mK5l55ObT8wrVM39AgJt9bdOdhcovNbIlTYHiT1A1Vzz+hFz5Bi3VdmOoWIynCkH1i48iLMG/vZisnd2TYGejGEHCCaZC64VGeCaJOBW2hn/FI4jO5+gLtv3MtWY3KUYi0SAt4EWus7YcLGC4Jxdyiis32GADK3gAiDKfG90xbVUldCysciTM7wvci12GYh3mcn3VajGIL19iWJUc/izHeeQzj7chhTist0qZhdvQIDAQAB";
    private String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmtPj23EPTcpi6S1BppKJzR5+4ab3CWROpYrgdDFlbpO/ia++ckFdJyrgEjpRE3L4veCGpmshC8bG2xH9UaI5edIuC5WXy0FkW2oKFR6dnPpSv22GM/xNFOIGOVqsex9tZbJbfhmyYuWpUbHXlga2KCxbQosYktuWjAEzb+sfFngGVaI7CS5NSUGFhR3xwpEzO8VoViPi66qVQavoxkreG/uKzoI9I7Ai3b5JK+vA+Gz7W+ZpAqJPZihdvmqaKIt7QL7A4+tV8OWzhELyaIsIJ33RfsGe+iLp7XAfh1dm4bfzFIcUS3ws2ZE0ywb3TCurXMYqeLjsiVr42wYjPiHwdAgMBAAECggEAJqC0crjMjnHIiqCjlRygqoaagokJ4amCdkD2LL7tkz+ZfqKt2tv8EXnkt7absq/3FAGcOUaWM0c+hyh9XUeoVr5SwZbhK/eggwgRBHnL2KiMwqkLu0zWECf24Ts3qY6y9lUNKd3a/vNEj2AdmVDOGqfiqQon/Ou2pUUemVJy8m5BwYLafp5tZk/Yvo5ns48I6recTaQiCTbwXlD0hSYVHhaOhtzIXLk5hjBKGasDNuvAmhqZM+XGnITTq4Z+wGZS3Ir4ynGZMn4FzzTXd41SZ/tucKjFmh3tSeCQjTBzKS+Y6je694UNCKy0TTFc9mANFs4qL+m58uD6KWUiRx/27QKBgQD/FgJQPB2W6Yw92vTS1fJA1irJLPeUoy3R99qDAlGjbnwht31943/DfhBtBGdEOUaXskJvtZLYwU4aE9CytUKQeqhuIU1uRosE44V3wq6EFTknXGFQ+XAT9e+hAjSn7HcHYjPRvxMJvmg3N/86oQAq/Zt6UNGmvCRwQzSdmdeUmwKBgQDniJm7v56xVuO/5W+P9yiNUayHgCfya7/eQij0Rw9hIJ0mKEUl2ENGGdtw6ZqsdrSD6yHkzGLTGCd0cy9cifwz25AjnSOf5kLHJem9WuxqcFlZQlF8X+ba1OOJwfQxyCrjhR2/1QttVGOl84kMDij/YYA0Ip+EQ5mP5YWxsfzRpwKBgGHX3VdNV8Q1HRf0zoe4jM2V2J+F4glfdgnd2jD6SLo5fN+p9Da/dphT2JUYZUvj9FHnjFgw+3ys2Ppjs19462ljwwtLNtofsN9VLjK7uBjt0xhn9OQMaZMeeKX3pJ7sstNgNaQ0eGb6ZBsaHYoti1TyJhsKuQANlqSBAsOY6PoVAoGAfpjIRkoeASVbXj+bYoJn3+16gpRmQzz7KhHOxtJb7GWix4xZUroO/rOAsxpoAkjdpwvX+nxuxLF+UUPx741bIxe4lmCDbBjBBpcKWQjwH7rSf+WtHG5rkde5mkc8uEOUf9b3Tz60AtGTsteYZckQTaMIMHmF8xHrUyzSjzic3h0CgYEApidFokFSKY39OLk9WqWLd+XfL1ykOJh4xsREADo09oIvVtRrtaojaDpCg6/ewb5eVHzIxutTJqrKNIiO00+jK9OBqHf3NIbVA2aAxj2+zTZCc/+zkqDjnhWFcKLU4hAH5j6mQrDcn7hzE5ic/nyILQOyO2hQVXqM82RFcIJPCkE=";
//    private String privateKeyStr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCg2axINyMVEb1Vh4K0ob/4Oq4BruAYgrXmnnvmZC9Te9nVau785zepgjBTuQAaPBSfVmcCwHwqP5eydCJGTuNoniF7m1LFliivK9rDYCMcMUDWYrmXnk5tPzCtUzf0CAm31t052Fyi81siVNgeJPUDVXPP6EXPkGLdV2Y6hYjKcKQfWLjyIswb+9mKyd3ZNgZ6MYQcIJpkLrhUZ4Jok4FbaGf8UjiM7n6Au2/cy1ZjcpRiLRIC3gRa6zthwsYLgnF3KKKzfYYAMreACIMp8b3TFtVSV0LKxyJMzvC9yLXYZiHeZyfdVqMYgvX2JYlRz+LMd55DOPtyGFOKy3SpmF29AgMBAAECggEAIfaUSBNMVxCN19drI9TbggBx6W0RaFuBKRbhtn4E+C24izWvDLuNpz8UETmI6gExoYtO6OfXz9vpngajjhDuN5LcIwti6NGR+Zra8FIGV9eWhy4FXhxRFwQ2tQJTWDWyR50YzTQAK3ATGCauE39eNLwAXi//PZqRJYdk0IqNnaDhYkwqstzzzykKDVo1sWp4EmglcMhW2iOw23LgPyrdqaeGHJjCcaGMKYlqVWRx3PTh57FktjRbJj0viuSL8/i6eQM+EHCkzkwpBegleVhbFvi0ok965ToaW6ve5RzT12fXHpm0JxBpdYTYMCbmAt3PJ0D5MqsuRG1cYbAO01bqAQKBgQDTcOfgCTYle20NWTs9CEtrPVLvsBO4DI22raMuwT7ko6KKOh2bWiuahrin/zZgIO64C9Gm38aXOoX552ewEBa2ElKIQ+wDtvzj89F6YBcJnuH+s5CuzKAZiE6LAThtMdF9c6p5yFWk0HYxNT3OI24C5j6UtoUnAPb/O5vGGpxPQQKBgQDCv26SaBkvmPUKwKqnjpK97Vp1B31VJKIsCCeaLIudfDf6LAuP8fmnWevq2incqs2Vu0SVEKVeWkss1KuLVcxUZFLYi6NR1gy7fytkE2mgDTZvx5WIB2JHOh5y9tfSxAnF7i5nGkjMvYSUHh++2TZvztYz/qd0ZfaUcnnIUkXrfQKBgQCtSKTf2OzFPygafnysAjY9Bx8HTJh8h91ET32i/cDbhLfa7NIC1Z1SoQKIvAIGOjcqIpWJW2/X1KCkNF+nzSevw8hb4L52NOuArfuJfWKkAdBY+zSh7RVwl2M3TKK/6sozNC4MbICz+BxFK8shO6wXODcljYgGdSX3+xQpAPoFgQKBgQCP2ow4xYBlMl0/dhYY8y3d8afx7nhcHlVUpSlrrx/qUsmemE5qI9nADqI/tqtZfymhQtVkLwmYaqZBFU8p+1SVug2gO3WGi9CX51YL9HL/cQMXCpygAKRN7NZ1IXVHENwPQuMv8bB43D1ElAPrG9QkPurnOWJEjtTXzr+6g1t2mQKBgGX2w7Wv+ypg5rMcMqskQ+W1CmaK1wk9tq0+5iFck//VX3/FHd7S4ROGoKi3EoZiHDzt54qtqPBLb1g7kZFHPzqUlT/ZXDm6RKtl7CcK+MLHAryyGVLYQH6gnUWazN7C2y9xO9Qpnak3s2cOCPadoiAgFDeCbJWNZXei322SpMaa";

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
        Assert.assertEquals(true, valid);
    }

    @Test
    public void testGetWaitSignStr() {
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
    public void testBuildHttpQuery() {
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
