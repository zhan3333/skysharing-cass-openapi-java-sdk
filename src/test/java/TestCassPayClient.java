import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.Signer;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.exception.ResponseNotValidException;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.model.RemitOrder;
import com.skysharing.api.request.*;
import com.skysharing.api.response.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

;

@Ignore
public class TestCassPayClient {

    private String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5rT49txD03KYuktQaaSic0efuGm9wlkTqWK4HQxZW6Tv4mvvnJBXScq4BI6URNy+L3ghqZrIQvGxtsR/VGiOXnSLguVl8tBZFtqChUenZz6Ur9thjP8TRTiBjlarHsfbWWyW34ZsmLlqVGx15YGtigsW0KLGJLblowBM2/rHxZ4BlWiOwkuTUlBhYUd8cKRMzvFaFYj4uuqlUGr6MZK3hv7is6CPSOwIt2+SSvrwPhs+1vmaQKiT2YoXb5qmiiLe0C+wOPrVfDls4RC8miLCCd90X7Bnvoi6e1wH4dXZuG38xSHFEt8LNmRNMsG90wrq1zGKni47Ila+NsGIz4h8HQIDAQAB";
    private String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDmtPj23EPTcpi6S1BppKJzR5+4ab3CWROpYrgdDFlbpO/ia++ckFdJyrgEjpRE3L4veCGpmshC8bG2xH9UaI5edIuC5WXy0FkW2oKFR6dnPpSv22GM/xNFOIGOVqsex9tZbJbfhmyYuWpUbHXlga2KCxbQosYktuWjAEzb+sfFngGVaI7CS5NSUGFhR3xwpEzO8VoViPi66qVQavoxkreG/uKzoI9I7Ai3b5JK+vA+Gz7W+ZpAqJPZihdvmqaKIt7QL7A4+tV8OWzhELyaIsIJ33RfsGe+iLp7XAfh1dm4bfzFIcUS3ws2ZE0ywb3TCurXMYqeLjsiVr42wYjPiHwdAgMBAAECggEAJqC0crjMjnHIiqCjlRygqoaagokJ4amCdkD2LL7tkz+ZfqKt2tv8EXnkt7absq/3FAGcOUaWM0c+hyh9XUeoVr5SwZbhK/eggwgRBHnL2KiMwqkLu0zWECf24Ts3qY6y9lUNKd3a/vNEj2AdmVDOGqfiqQon/Ou2pUUemVJy8m5BwYLafp5tZk/Yvo5ns48I6recTaQiCTbwXlD0hSYVHhaOhtzIXLk5hjBKGasDNuvAmhqZM+XGnITTq4Z+wGZS3Ir4ynGZMn4FzzTXd41SZ/tucKjFmh3tSeCQjTBzKS+Y6je694UNCKy0TTFc9mANFs4qL+m58uD6KWUiRx/27QKBgQD/FgJQPB2W6Yw92vTS1fJA1irJLPeUoy3R99qDAlGjbnwht31943/DfhBtBGdEOUaXskJvtZLYwU4aE9CytUKQeqhuIU1uRosE44V3wq6EFTknXGFQ+XAT9e+hAjSn7HcHYjPRvxMJvmg3N/86oQAq/Zt6UNGmvCRwQzSdmdeUmwKBgQDniJm7v56xVuO/5W+P9yiNUayHgCfya7/eQij0Rw9hIJ0mKEUl2ENGGdtw6ZqsdrSD6yHkzGLTGCd0cy9cifwz25AjnSOf5kLHJem9WuxqcFlZQlF8X+ba1OOJwfQxyCrjhR2/1QttVGOl84kMDij/YYA0Ip+EQ5mP5YWxsfzRpwKBgGHX3VdNV8Q1HRf0zoe4jM2V2J+F4glfdgnd2jD6SLo5fN+p9Da/dphT2JUYZUvj9FHnjFgw+3ys2Ppjs19462ljwwtLNtofsN9VLjK7uBjt0xhn9OQMaZMeeKX3pJ7sstNgNaQ0eGb6ZBsaHYoti1TyJhsKuQANlqSBAsOY6PoVAoGAfpjIRkoeASVbXj+bYoJn3+16gpRmQzz7KhHOxtJb7GWix4xZUroO/rOAsxpoAkjdpwvX+nxuxLF+UUPx741bIxe4lmCDbBjBBpcKWQjwH7rSf+WtHG5rkde5mkc8uEOUf9b3Tz60AtGTsteYZckQTaMIMHmF8xHrUyzSjzic3h0CgYEApidFokFSKY39OLk9WqWLd+XfL1ykOJh4xsREADo09oIvVtRrtaojaDpCg6/ewb5eVHzIxutTJqrKNIiO00+jK9OBqHf3NIbVA2aAxj2+zTZCc/+zkqDjnhWFcKLU4hAH5j6mQrDcn7hzE5ic/nyILQOyO2hQVXqM82RFcIJPCkE=";
    private String vzhuoPublicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzvaToLe+vIN/G4u9gxe3kL/myE129RsgZWIyTf7Tjc0rARdsvE9Xy2fdzNTwttyqJJaB9oNuCjdhNXAJoSUqoKei0aA88oFylPWG6DZ7ZoIXOGtipvnloosWhskes+E+EvQWrwSgzCAEr5N7iHY5DEBBy8ZoHsthd5CvCGyBgfeAsCCQ/R6Cl2Bw0SNasVu45+0PfmW/AUeC29i7KJm1HC/D5n6oXSa6q16oh6TWiFnVJF5dNCeuHN+3KQAe/Hv3CldiTHB8mND9OYzx2DzZYJD7g8f185Td5a8mRwlYf2PwTfGLkMdxa+XlGIsHjCFbXD1N85XC3QVfbgZWnxQA9QIDAQAB";
    private Signer signer = new Signer();
    private String url = "http://fuwu-openapi.skysharing.cn/gateway/cass";
    private String APPID = "12345H29GDK580CGNO30G81OEB";


    private DefaultCassPayClient client;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        System.out.println("start test");
        this.client = new DefaultCassPayClient(this.url, this.APPID, this.privateKeyStr, this.vzhuoPublicKeyStr, "JSON", "RSA2");
    }

    @Test
    public void testCreateDefaultClient() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        new DefaultCassPayClient(this.url, this.APPID, this.privateKeyStr, this.vzhuoPublicKeyStr, "JSON", "RSA2");
        new DefaultCassPayClient(this.url, this.APPID, this.privateKeyStr, this.vzhuoPublicKeyStr);
    }

    @Test
    public void testGetBankBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        GetBalanceResponse response = this.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.bank.bankAccout);
        assertNotEquals("", response.bank.lockedAmt);
        assertNotEquals("", response.bank.canUseAmt);
        assertNotEquals("", response.bank.childFAbalance);
        assertNotEquals("", response.bank.mandatoryName);
        assertTrue(response.verify());
    }

    @Test
    public void testPayBankRemit() throws Exception {
        PayBankRemitRequest request = new PayBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        BankPayOrder[] orders = new BankPayOrder[]{
                new BankPayOrder("123456", "12345678910", "詹光", "1.00")
        };
        request.setOrders(orders);
        PayBankRemitResponse response = this.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testGetOneRemitStatus() throws ResponseNotValidException {
        BankPayOrder bankPayOrder = new BankPayOrder("123456", "12345678910", "123光", "1.00");
        PayBankRemitResponse payResponse = this.client.execute((new PayBankRemitRequest()).setPayChannelK(GetBalanceRequest.BANK).setOrders(new BankPayOrder[]{
                bankPayOrder
        }));
        ;
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusRequest request = new GetOneRemitStatusRequest();
        request.setRbUUID(rbUUID);
        GetOneRemitStatusResponse response = this.client.execute(request);
        assertEquals(rbUUID, response.rbUUID);
        RemitOrder remitOrder = response.remitOrders.get(0);
        assertNotNull(remitOrder);
        assertEquals(bankPayOrder.orderSN, remitOrder.orderSN);
    }

    @Test
    public void testGetOneOrderStatusByBank() throws Exception {
        BankPayOrder bankPayOrder = new BankPayOrder("123456", "12345678910", "詹光", "1.00");
        PayBankRemitResponse payResponse = this.client.execute((new PayBankRemitRequest()).setPayChannelK(GetBalanceRequest.BANK).setOrders(new BankPayOrder[]{
                bankPayOrder
        }));
        ;

        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.client.execute(oneOrderStatusRequest);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(bankPayOrder.orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }

    @Test
    public void testGetOneOrderStatusByAliPay() throws Exception {
        AliPayOrder aliPayOrder = new AliPayOrder("123456", "13517210601", "詹光1", "1.00");
        PayAliRemitResponse payResponse = this.client.execute(
                (new PayAliRemitRequest())
                        .setPayChannelK(GetBalanceRequest.AliPay)
                        .setOrders(new AliPayOrder[]{
                                aliPayOrder
                        }));
        System.out.println(payResponse.raw);
        assertNotNull(payResponse.rbUUID);
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.client.execute(oneOrderStatusRequest);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(aliPayOrder.orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }

    @Test
    /**
     * 测试获取通道充值数据
     */
    public void testGetChannelData() throws ResponseNotValidException {
        GetChannelDataRequest request = new GetChannelDataRequest();
        request.setPayChannelK(GetChannelDataRequest.BANK);
        GetChannelDataResponse response = this.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.accountName);
        assertNotNull( response.bankAccount);
        assertNotNull( response.bankCardNO);
    }

    @Test
    /**
     * 测试提交支付宝充值申请
     */
    public void testChargeAliPay() throws ResponseNotValidException {
        ChargeAliPayRequest request = new ChargeAliPayRequest();
        request.setApplyAmount("0.1")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一毛钱")
                .setRechargePic("123124123");
        ChargeAliPayResponse response = this.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);
    }

    @Test
    /**
     * 测试提交银行卡充值申请
     */
    public void testChargeBank() throws ResponseNotValidException {
        ChargeBankRequest request = new ChargeBankRequest();
        request.setApplyAmount("0.01")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一分钱")
                .setRechargePic("123124123");
        ChargeBankResponse response = this.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);
    }

    @Test
    public void testGetChargeResult() throws ResponseNotValidException {
        ChargeBankRequest request = new ChargeBankRequest();
        request.setApplyAmount("0.02")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值两分钱")
                .setRechargePic("123124123");
        ChargeBankResponse response = this.client.execute(request);
        String rechargeSBSN = response.rechargeSBSN;

        GetChargeResultRequest request1 = new GetChargeResultRequest();
        request1.setRechargeSBSN(rechargeSBSN);
        GetChargeResultResponse response1 = this.client.execute(request1);
        System.out.println(response1);

        assertEquals("10000", response1.code);
        assertNotNull(response1.status);
        assertNotNull(response1.applyAmount);
        assertNotNull(response1.realAmount);
        assertEquals(rechargeSBSN, response1.rechargeSBSN);
    }
}
