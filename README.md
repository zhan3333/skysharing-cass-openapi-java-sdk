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
  <version>1.0-SNAPSHOT</version>
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
            }
        }
    }
}

```

## 接口对应请求和响应列表

|  接口名称   | 请求  | 响应|
|  ----  | ----  | --- |
|获取商户余额| GetBalanceRequest  | GetBalanceResponse |
|获取单个订单状态| GetOneOrderStatusRequest  | GetOneOrderStatusResponse |
|获取单个批次状态| GetOneRemitStatusRequest  | GetOneRemitStatusResponse |
|支付宝实时下单| PayAliRemitRequest  | PayAliRemitResponse |
|银行卡实时下单| PayBankRemitRequest  | PayBankRemitResponse |
|支付宝充值申请提交| ChargeBankRequest  | ChargeBankResponse |
|银行卡充值申请提交| ChargeAliPayRequest  | ChargeAliPayResponse |
|获取充值结果| GetChargeResultRequest  | GetChargeResultResponse |
|充值账号查询| GetChannelDataRequest  | GetChannelDataResponse |
|获取用户实名认证状态| GetUsersVerifyStatusRequest  | GetUsersVerifyStatusResponse |
|添加用户实名认证状态| VerifyUserRequest  | VerifyUserResponse |
