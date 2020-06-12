# Skysharing CASS open api JAVA SDK

## 准备

1. 商户的私钥字符串 `appPrivateKey`, 无头尾信息及换行, pkcs8 格式, 下为示例
`MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmtPj23EPTcpi6S1BppKJzR5+4ab3CWROpYrgdDFlbpO/ia++ckFdJyrgEjpRE3L4veCGpmshC8bG2xH9UaI5edIuC5WXy0FkW2oKFR6dnPpSv22GM/xNFOIGOVqsex9tZbJbfhmyYuWpUbHXlga2KCxbQosYktuWjAEzb+sfFngGVaI7CS5NSUGFhR3xwpEzO8VoViPi66qVQavoxkreG/uKzoI9I7Ai3b5JK+vA+Gz7W+ZpAqJPZihdvmqaKIt7QL7A4+tV8OWzhELyaIsIJ33RfsGe+iLp7XAfh1dm4bfzFIcUS3ws2ZE0ywb3TCurXMYqeLjsiVr42wYjPiHwdAgMBAAECggEAJqC0crjMjnHIiqCjlRygqoaagokJ4amCdkD2LL7tkz+ZfqKt2tv8EXnkt7absq/3FAGcOUaWM0c+hyh9XUeoVr5SwZbhK/eggwgRBHnL2KiMwqkLu0zWECf24Ts3qY6y9lUNKd3a/vNEj2AdmVDOGqfiqQon/Ou2pUUemVJy8m5BwYLafp5tZk/Yvo5ns48I6recTaQiCTbwXlD0hSYVHhaOhtzIXLk5hjBKGasDNuvAmhqZM+XGnITTq4Z+wGZS3Ir4ynGZMn4FzzTXd41SZ/tucKjFmh3tSeCQjTBzKS+Y6je694UNCKy0TTFc9mANFs4qL+m58uD6KWUiRx/27QKBgQD/FgJQPB2W6Yw92vTS1fJA1irJLPeUoy3R99qDAlGjbnwht31943/DfhBtBGdEOUaXskJvtZLYwU4aE9CytUKQeqhuIU1uRosE44V3wq6EFTknXGFQ+XAT9e+hAjSn7HcHYjPRvxMJvmg3N/86oQAq/Zt6UNGmvCRwQzSdmdeUmwKBgQDniJm7v56xVuO/5W+P9yiNUayHgCfya7/eQij0Rw9hIJ0mKEUl2ENGGdtw6ZqsdrSD6yHkzGLTGCd0cy9cifwz25AjnSOf5kLHJem9WuxqcFlZQlF8X+ba1OOJwfQxyCrjhR2/1QttVGOl84kMDij/YYA0Ip+EQ5mP5YWxsfzRpwKBgGHX3VdNV8Q1HRf0zoe4jM2V2J+F4glfdgnd2jD6SLo5fN+p9Da/dphT2JUYZUvj9FHnjFgw+3ys2Ppjs19462ljwwtLNtofsN9VLjK7uBjt0xhn9OQMaZMeeKX3pJ7sstNgNaQ0eGb6ZBsaHYoti1TyJhsKuQANlqSBAsOY6PoVAoGAfpjIRkoeASVbXj+bYoJn3+16gpRmQzz7KhHOxtJb7GWix4xZUroO/rOAsxpoAkjdpwvX+nxuxLF+UUPx741bIxe4lmCDbBjBBpcKWQjwH7rSf+WtHG5rkde5mkc8uEOUf9b3Tz60AtGTsteYZckQTaMIMHmF8xHrUyzSjzic3h0CgYEApidFokFSKY39OLk9WqWLd+XfL1ykOJh4xsREADo09oIvVtRrtaojaDpCg6/ewb5eVHzIxutTJqrKNIiO00+jK9OBqHf3NIbVA2aAxj2+zTZCc/+zkqDjnhWFcKLU4hAH5j6mQrDcn7hzE5ic/nyILQOyO2hQVXqM82RFcIJPCkE=`
2. CASS系统提供的公钥 `cassPublicKey`, 无头尾信息及换行, 下为示例
`MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzvaToLe+vIN/G4u9gxe3kL/myE129RsgZWIyTf7Tjc0rARdsvE9Xy2fdzNTwttyqJJaB9oNuCjdhNXAJoSUqoKei0aA88oFylPWG6DZ7ZoIXOGtipvnloosWhskes+E+EvQWrwSgzCAEr5N7iHY5DEBBy8ZoHsthd5CvCGyBgfeAsCCQ/R6Cl2Bw0SNasVu45+0PfmW/AUeC29i7KJm1HC/D5n6oXSa6q16oh6TWiFnVJF5dNCeuHN+3KQAe/Hv3CldiTHB8mND9OYzx2DzZYJD7g8f185Td5a8mRwlYf2PwTfGLkMdxa+XlGIsHjCFbXD1N85XC3QVfbgZWnxQA9QIDAQAB`
3. CASS提供的商户 `APPID`
4. CASS open api 调用地址 `https://fuwu-openapi.skysharing.cn/gateway/cass`

##  maven 引用包

```xml
<dependency>
  <groupId>com.github.zhan3333</groupId>
  <artifactId>skysharing-cass-sdk-java</artifactId>
  <version>2.3.1</version>
</dependency>
```

## 简单调用示例

```JAVA
class Test {
    public void queryBalance() {
        // 商户私钥
        String privateKeyStr = "";
        // CASS系统公钥
        String cassPublicKeyStr = "";
        // APPID
        String APPID = "12345H29GDK580CGNO30G81OEB";
        // 实例化客户端, format 暂时只支持JSON, signType 暂时只支持RSA2
        DefaultCassPayClient client = new DefaultCassPayClient("https://fuwu-openapi.skysharing.cn/gateway/cass", APPID, privateKeyStr, cassPublicKeyStr, "JSON", "RSA2");
        // 创建接口请求
        GetBalanceRequest request = new GetBalanceRequest();
        // 设置请求参数
        request.setPayChannelK(GetBalanceRequest.BANK);
        // 执行请求,获取响应值
        GetBalanceResponse response = client.execute(request);
        // 验签
        if (response.verify()) {
            // 验签通过
            if (response.isSuccess()) {
                // 业务请求成功 ...   
                System.println(response) 
            }
        }
    }
}

```

## 接口对应请求和响应列表

|  接口名称   | 请求  | 响应|
|  ----  | ----  | --- |
|获取商户余额         | GetBalanceRequest             | GetBalanceResponse |
|获取单个批次状态       | GetOneRemitStatusRequest      | GetOneRemitStatusResponse |
|获取单个订单状态      | GetOneOrderStatusRequest       | GetOneOrderStatusResponse |
|银行卡实时下单        | PayBankRemitRequest               | PayBankRemitResponse |
|微信实时下单        | PayWeChatRemitRequest                | PayWeChatRemitResponse |
|单笔银行卡实时下单          | PayOneBankRemitRequest            | PayOneBankRemitRequest |
|单笔微信实时下单          | PayOneWeChatRemitRequest             | PayOneWeChatRemitRequest |
|充值账号查询             | GetChannelDataRequest                 | GetChannelDataResponse |
|银行卡充值申请提交      | ChargeBankRequest                     | ChargeBankResponse |
|微信充值申请提交      | ChargeWeChatPayRequest                   | ChargeWeChatPayResponse |
|获取充值结果             | GetChargeResultRequest                | GetChargeResultResponse |
|添加用户实名认证状态         | VerifyUserRequest                 | VerifyUserResponse |
|获取用户实名认证状态         | GetUsersVerifyStatusRequest       | GetUsersVerifyStatusResponse |
|获取合同列表         | GetContractListRequest                    | GetContractListResponse |
|批量获取用户电签状态         | GetUserEsignStatusRequest                    | GetUserEsignStatusResponse |
|获取商户电签二维码         | GetShowSignUrlRequest                    | GetShowSignUrlResponse |

## 解析异步通知数据

可以将接收到的 json 文本直接创建 Notify.class, 可以解析出来对象便于操作

```
String str = "{\"response\":{\"charset\":\"UTF-8\",\"content\":\"{\\\"resourceID\\\":\\\"18483279179157507UBO8C1GBI8S4RUJ\\\",\\\"pushType\\\":2,\\\"notifyUrl\\\":\\\"http:\\\\\\/\\\\\\/docker.for.mac.localhost:7777\\\\\\/api\\\\\\/conversion_skysharing\\\\\\/v1\\\\\\/notify\\\",\\\"sendData\\\":\\\"{\\\\\\\"status\\\\\\\":\\\\\\\"TRADE_FAILED\\\\\\\"}\\\",\\\"createdAt\\\":1585742840681725,\\\"orderSN\\\":\\\"8D9B9AE0-6DDD-4D72-9334-18AD72029500\\\",\\\"rbUUID\\\":\\\"18483278357073921OA5IDCFFO2RO62L\\\"}\",\"notifyTime\":\"20200608143859\",\"notifyType\":2,\"signType\":\"RSA2\"},\"sign\":\"gV9P7jvBCX6qc8xRRfIAfi357Wpc9Rmt9MfOwa4QucRU3\\/WSf525WFlyfEt3BqeJ1qsDWGHLoH5l++77kUPL0qRcLc6InC6G1uxERaaJ8yPPZa9246mJJo3SyLr+3ZT5bq5mLYhOOx0Zj2GR0+08luLHB29f72xdXTNWvitro9o48O4RJ0CeldIG89J1N683ZXlRSIfUcIBoHWEUHlYV8sDZlQlAMcc9+dIjsg\\/5RGjkhJHI2LMKldJrvK8jCb1pruS+i4f51gatcdqwoDZ0zWy1xTaywPT4PdaBU6emB4Xn4kyW6NBCLFVEU0bIg9CNZXZ6TXSN5H6Kbl8ppJuKdA==\"}";
Notify n = new Notify(str);
System.out.printf("notify object: %s", n);
```

## 异步通知签名校验

可以直接对通知请求体进行校验签名，内部会自动解码处理

```
Map<String, String> map = new HashMap<>();
try {
    DefaultCassPayClient client = new DefaultCassPayClient(dotenv.get("API_URL"), dotenv.get("APPID"), dotenv.get("PRIVATE_KEY_STR"), dotenv.get("VZHUO_PUBLIC_KEY_STR"));
    client.setDebug(true);
    boolean ret = client.verifyNotify(postStr);
    System.out.printf("response verify: %b", ret);
    if (ret) {
        map.put("content", "success");
    } else {
        map.put("content", "failed");
    }
} catch (InvalidPrivateKeyException | InvalidPublicKeyException e) {
    e.printStackTrace();
}
// 需要向客户端返回 success, failed, 为 failed 时将会按照一定频率重试通知，
return map
```