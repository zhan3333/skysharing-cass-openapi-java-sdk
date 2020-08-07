import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.*;
import com.skysharing.api.model.*;
import com.skysharing.api.request.*;
import com.skysharing.api.response.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestCassPayClient {

    private BeforeParams beforeParams;


    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    @Test
    public void testGetEnv() {
        String APPID = this.beforeParams.dotenv.get("APPID");
        System.out.println(APPID);
    }

    @Test
    public void testCreateDefaultClient() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        new DefaultCassPayClient(this.beforeParams.url, this.beforeParams.APPID, this.beforeParams.privateKeyStr, this.beforeParams.vzhuoPublicKeyStr, "JSON", "RSA2");
        new DefaultCassPayClient(this.beforeParams.url, this.beforeParams.APPID, this.beforeParams.privateKeyStr, this.beforeParams.vzhuoPublicKeyStr);
    }

    @Test
    public void testGetBankBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        request.setPayChannelK(null);
        GetBalanceResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.bank.lockedAmt);
        assertNotEquals("", response.bank.canUseAmt);
        assertNotEquals("", response.bank.childFAbalance);
        assertTrue(response.verify());
    }

    @Test
    public void testGetWeChatBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(GetBalanceRequest.Wecaht);
        request.setPayChannelK(null);
        GetBalanceResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.bank.lockedAmt);
        assertNotEquals("", response.bank.canUseAmt);
        assertNotEquals("", response.bank.childFAbalance);
        assertTrue(response.verify());
    }


    @Test
    public void testGetBankBalanceWithNullChannelK() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(null);
        GetBalanceResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.bank.lockedAmt);
        assertNotEquals("", response.bank.canUseAmt);
        assertNotEquals("", response.bank.childFAbalance);
        assertTrue(response.verify());
    }

    @Test
    public void testPayBankRemitByWangShang() throws Exception {
        PayBankRemitRequest request = new PayBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
//        request.setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_ALI_PAY);
        List<BankPayOrder> orders = new ArrayList<>();

        orders.add(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "詹光1",
                        "0.1"
                )
                        .setIdentityCard("420222199212041057")
                        .setNotifyUrl("http://www.baidu.com")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        request.setContractID("13");
//        request.setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD);
        PayBankRemitResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testPayBankRemit() throws Exception {
        PayBankRemitRequest request = new PayBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        List<BankPayOrder> orders = new ArrayList<>();

        orders.add(new BankPayOrder(
                UUID.randomUUID().toString().toUpperCase(),
                "6214850271449677",
                "谢丽1",
                "0.01")
                .setIdentityCard("420222199212041057")
                .setNotifyUrl("http://127.0.0.1:7777")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        request.setContractID("13");
//        request.setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD);
        PayBankRemitResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testPayOneBankRemit() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        PayOneBankRemitRequest request = new PayOneBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
//        request.setPayeeChannelType(PayOneBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD);
        request.setOrder(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "6214850271449677",
                        "谢丽1",
                        "0.02"
                )
                        .setIdentityCard("420222199212041058")
                        .setNotifyUrl("http://www.baidu.com")
        );
        request.setContractID("12");
        PayOneBankRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.toString(), "10000", response.code);
        assertNotNull(response.toString(), response.rbUUID);
    }

    // 网商银行打款到支付宝
    @Test
    public void testPayOneBankRemitToAli() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        PayOneBankRemitRequest request = new PayOneBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
//        request.setPayeeChannelType(PayOneBankRemitRequest.PAYEE_CHANNEL_TYPE_ALI_PAY);
        request.setOrder(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "1234567946767437",
                        "李生",
                        "0.94"
                )
//                        .setIdentityCard("420222199212041058")
                        .setNotifyUrl("http://www.baidu.com")
        );
//        request.setContractID("12");
        PayOneBankRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.toString(), "10000", response.code);
        assertNotNull(response.toString(), response.rbUUID);
    }

    @Test
    public void testPayOneAliRemit() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        PayOneAliRemitRequest request = new PayOneAliRemitRequest();
        request.setPayChannelK(GetBalanceRequest.AliPay);
        request.setOrder(
                new AliPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "詹光11",
                        "0.20"
                ).setIdentityCard("420222199212041057")
        );
        request.setContractID("12");
        PayOneAliRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.toString(), "10000", response.code);
        assertNotNull(response.toString(), response.rbUUID);
    }

    @Test
    public void testGetOneRemitStatus() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        List<BankPayOrder> orders = new ArrayList<>();
        orders.add(new BankPayOrder(
                UUID.randomUUID().toString().toUpperCase(),
                "12345678910",
                "123光",
                "1.00")
                .setIdentityCard("420222199212041057")
                .setNotifyUrl("http://127.0.0.1:7777")
        );
        PayBankRemitResponse payResponse = this.beforeParams.client
                .execute(
                        (new PayBankRemitRequest())
                                .setPayChannelK(GetBalanceRequest.BANK)
                                .setOrders(orders)
//                                .setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD)
                );
        assertEquals(payResponse.message + "/" + payResponse.subMsg, payResponse.code, "10000");
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusRequest request = new GetOneRemitStatusRequest();
        request.setRbUUID(rbUUID);
        GetOneRemitStatusResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.message + "/" + response.subMsg, response.code, "10000");
        assertEquals(rbUUID, response.rbUUID);
        RemitOrder remitOrder = response.remitOrders.get(0);
        assertNotNull(remitOrder);
        assertNotNull(response.totalServiceCharge);
        assertNotNull(response.totalRealPayAmount);
        assertNotNull(response.totalExpectAmount);
        assertEquals(orders.get(0).orderSN, remitOrder.orderSN);
    }

    @Test
    public void testGetOneOrderStatusByBank() throws Exception {
        List<BankPayOrder> orders = new ArrayList<>();
        orders.add(
                new BankPayOrder(UUID.randomUUID().toString().toUpperCase(),
                        "12345678910",
                        "詹光",
                        "1.00")
                        .setIdentityCard("420222199212041057")
                        .setNotifyUrl("http://127.0.0.1:7777")
        );
        PayBankRemitResponse payResponse = this.beforeParams.client
                .execute(
                        (new PayBankRemitRequest())
                                .setPayChannelK(GetBalanceRequest.BANK)
                                .setOrders(orders)
//                                .setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD)
                );
        assertEquals(payResponse.message + "/" + payResponse.subMsg, "10000", payResponse.code);
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.beforeParams.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        assertEquals(response.toString(), "10000", response.code);
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.beforeParams.client.execute(oneOrderStatusRequest);
        assertEquals(getOneOrderStatusResponse.toString(), "10000", response.code);
        assertNotNull(getOneOrderStatusResponse.rbUUID);
        assertNotNull(getOneOrderStatusResponse.orderUUID);
        assertNotNull(getOneOrderStatusResponse.orderSN);
        assertNotNull(getOneOrderStatusResponse.remitStatus);
        assertNotNull(getOneOrderStatusResponse.orderStatus);
        assertNotNull(getOneOrderStatusResponse.toString(), getOneOrderStatusResponse.reachAt);
        assertNotNull(getOneOrderStatusResponse.responseMsg);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(orders.get(0).orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }

    @Test
    public void testGetOneOrderStatusByAliPay() throws Exception {
        AliPayOrder aliPayOrder = new AliPayOrder(UUID.randomUUID().toString().toUpperCase(), "18627981216", "彭思琴11", "0.10").setIdentityCard("420222199212041057");
        PayAliRemitResponse payResponse = this.beforeParams.client.execute(
                (new PayAliRemitRequest())
                        .setPayChannelK(GetBalanceRequest.AliPay)
                        .setOrders(new ArrayList<AliPayOrder>() {{
                            add(aliPayOrder);
                        }}).setContractID("15")
        );
        System.out.println(payResponse.raw);
        assertNotNull(payResponse.rbUUID);
        String rbUUID = payResponse.rbUUID;
        GetOneRemitStatusResponse response = this.beforeParams.client.execute(new GetOneRemitStatusRequest().setRbUUID(rbUUID));
        RemitOrder remitOrder = response.remitOrders.get(0);
        String orderUUID = remitOrder.orderUUID;
        GetOneOrderStatusRequest oneOrderStatusRequest = new GetOneOrderStatusRequest();
        oneOrderStatusRequest.setOrderUUID(orderUUID);
        GetOneOrderStatusResponse getOneOrderStatusResponse = this.beforeParams.client.execute(oneOrderStatusRequest);
        assertTrue(getOneOrderStatusResponse.verify());
        assertEquals(aliPayOrder.orderSN, getOneOrderStatusResponse.orderSN);
        assertEquals(rbUUID, getOneOrderStatusResponse.rbUUID);
    }

    /**
     * 测试获取通道充值数据
     */
    @Test
    public void testGetChannelData() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        GetChannelDataRequest request = new GetChannelDataRequest();
        request.setPayChannelK(GetChannelDataRequest.BANK);
        GetChannelDataResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.accountName);
        assertNotNull(response.bankAccount);
        assertNotNull(response.bankCardNO);
    }

    /**
     * 测试提交支付宝充值申请
     */
    @Test
    public void testChargeAliPay() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        ChargeAliPayRequest request = new ChargeAliPayRequest();
        request.setApplyAmount("0.1")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一毛钱")
                .setRechargePic(this.beforeParams.frontSB.toString());
        ChargeAliPayResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);
    }

    @Test
    // 测试提交银行卡充值申请
    public void testChargeBank() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        ChargeBankRequest request = new ChargeBankRequest();
        request.setApplyAmount("0.01")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一分钱");
        ChargeBankResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);
    }

    @Test
    public void testGetChargeResultByAli() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        ChargeAliPayRequest request = new ChargeAliPayRequest();
        request.setApplyAmount("0.1")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一毛钱")
                .setRechargePic(this.beforeParams.frontSB.toString());
        ChargeAliPayResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);

        String rechargeSBSN = response.rechargeSBSN;
        System.out.println(rechargeSBSN);
        GetChargeResultRequest request1 = new GetChargeResultRequest();
        request1.setRechargeSBSN(rechargeSBSN);
        GetChargeResultResponse response1 = this.beforeParams.client.execute(request1);
        System.out.println(response1);

        assertEquals("10000", response1.code);
        assertNotNull(response1.status);
        assertNotNull(response1.applyAmount);
        assertNotNull(response1.realAmount);
        assertEquals(rechargeSBSN, response1.rechargeSBSN);
    }

    @Test
    public void testGetChargeResult() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        ChargeBankRequest request = new ChargeBankRequest();
        request.setApplyAmount("0.02")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值两分钱");
        ChargeBankResponse response = this.beforeParams.client.execute(request);
        String rechargeSBSN = response.rechargeSBSN;
        System.out.println(rechargeSBSN);
        GetChargeResultRequest request1 = new GetChargeResultRequest();
        request1.setRechargeSBSN(rechargeSBSN);
        GetChargeResultResponse response1 = this.beforeParams.client.execute(request1);
        System.out.println(response1);

        assertEquals("10000", response1.code);
        assertNotNull(response1.status);
        assertNotNull(response1.applyAmount);
        assertNotNull(response1.realAmount);
        assertEquals(rechargeSBSN, response1.rechargeSBSN);
    }

    // 3.13 查询认证状态
    @Ignore
    @Test
    public void testGetUsersVerifyStatus() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        GetUsersVerifyStatusRequest request = new GetUsersVerifyStatusRequest();

        request.addItem(new GetUsersVerifyStatusRequest.Item("420222199212041057", "429006199112162721", 1));

        GetUsersVerifyStatusResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.items);
        GetUsersVerifyStatusResponse.Item user = response.items.get(0);
        assertNotNull(user.identityCard);
        assertNotNull(user.receiptFANO);
        assertNotNull(user.receiptType);
        assertNotNull(user.status);
        assertFalse(user.exists);
        assertNotNull(user.identityCard);
        assertEquals("", user.verifiedAt);
        assertEquals("", user.failedMessage);
    }

    // 3.12 提交认证数据
    @Test
    public void testVerifyUser() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        VerifyUserRequest request = new VerifyUserRequest();
        request
                .setName("彭思琴")
                .setIdentityCard("429006199112162721")
                .setReceiptFANO(UUID.randomUUID().toString())
                .setReceiptType(1)
                .setFrontImgBase64(this.beforeParams.frontSB.toString())
                .setBackImgBase64(this.beforeParams.backSB.toString())
                .setLatitude("1234")
                .setLongitude("4321")
                .setPhone("13517210601");
        VerifyUserResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals(response.message + "/" + response.subMsg, "10000", response.code);
    }

    @Test
    public void testURLEncode() throws UnsupportedEncodingException {
        String newStr = URLEncoder.encode("!*'();:@&=+$,/?%#[]", "UTF-8");
        // php               %21%2A%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%25%23%5B%5D
        assertEquals("%21*%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%25%23%5B%5D", newStr);
    }

    @Test
    public void testJsonEncode() {
        // 对 '/' 进行转义
        String newStr = JSON.toJSONString("!*'();:@&=+$,/?%#[]", SerializerFeature.WriteSlashAsSpecial);
        assertEquals("\"!*'();:@&=+$,\\/?%#[]\"", newStr);
    }

    @Test
    public void batchPayAliRemit() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        for (int i = 0; i < 5; i++) {
            AliPayOrder aliPayOrder = new AliPayOrder(UUID.randomUUID().toString().toUpperCase(), "18627981216", "彭思琴11", "0.10").setIdentityCard("420222199212041057");
            PayAliRemitResponse payResponse = this.beforeParams.client.execute(
                    (new PayAliRemitRequest())
                            .setPayChannelK(GetBalanceRequest.AliPay)
                            .setOrders(new ArrayList<AliPayOrder>() {{
                                add(aliPayOrder);
                            }}).setContractID("11"));
            System.out.println(payResponse.raw);
        }
    }

    @Test
    // 批量提交银行卡打款
    public void batchPayBankRemit() throws InterruptedException {
        ArrayList<Thread> arr = new ArrayList<>();
        long t1 = new Date().getTime();

        for (int i = 0; i < Integer.parseInt(this.beforeParams.dotenv.get("TEST_REQUEST_THREAD_NUM", "1")); i++) {
            Thread t = new Thread(new OneBankRemitThread(this.beforeParams.client, i));
            t.start();
            arr.add(t);
            System.out.println("Index " + i + " ready");
        }
        for (Thread t : arr) {
            t.join();
        }
        long t2 = new Date().getTime();
        System.out.println("Use time: " + (t2 - t1));
    }

    @Test
    // 测试获取合同列表
    public void getContractList() throws SignException, RequestFailedException, ResponseNotValidException, RequestTimeoutException {
        GetContractListResponse response = this.beforeParams.client.execute(
                (new GetContractListRequest()).setPage(1).setSize(10)
        );
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotEquals(0, response.total);
        assertNotNull(response.data);
        assertNotEquals(0, response.data.size());
        assertNotNull(response.data.get(0));
        assertNotEquals(0, response.data.get(0).ID);
        assertNotEquals("", response.data.get(0).name);
    }

    @Test
    public void testWeChatRemit() throws Exception {
        PayWeChatRemitRequest request = new PayWeChatRemitRequest();
        List<WeChatOrder> orders = new ArrayList<>();

        orders.add(
                new WeChatOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "詹光",
                        "1",
                        "http://www.baidu.com")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        request.setContractID("13");
        PayWeChatRemitResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testOneWeChatRemit() throws Exception {
        PayOneWeChatRemitRequest request = new PayOneWeChatRemitRequest();
        request.setOrder(new WeChatOrder(
                UUID.randomUUID().toString().toUpperCase(),
                "13517210601",
                "詹光",
                "1",
                "http://www.baidu.com"
        ));
        request.setContractID("13");

        PayOneWeChatRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    @Test
    public void testChargeWeChat() throws SignException, RequestFailedException, ResponseNotValidException, RequestTimeoutException {
        ChargeWeChatRequest request = new ChargeWeChatRequest();
        request.setApplyAmount("100")
                .setBankAccount("中国银行")
                .setBankCardNO("6217001780012364")
                .setOrderName("充值一毛钱")
                .setRechargePic(this.beforeParams.frontSB.toString());
        ChargeWeChatResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals("10000", response.code);
        assertNotNull(response.rechargeSBSN);
    }

    @Test
    public void parseNotify() throws Exception {
        String str = "{\"response\":{\"charset\":\"UTF-8\",\"content\":\"{\\\"resourceID\\\":\\\"18483279179157507UBO8C1GBI8S4RUJ\\\",\\\"pushType\\\":2,\\\"notifyUrl\\\":\\\"http:\\\\\\/\\\\\\/docker.for.mac.localhost:7777\\\\\\/api\\\\\\/conversion_skysharing\\\\\\/v1\\\\\\/notify\\\",\\\"sendData\\\":\\\"{\\\\\\\"status\\\\\\\":\\\\\\\"TRADE_FAILED\\\\\\\"}\\\",\\\"createdAt\\\":1585742840681725,\\\"orderSN\\\":\\\"8D9B9AE0-6DDD-4D72-9334-18AD72029500\\\",\\\"rbUUID\\\":\\\"18483278357073921OA5IDCFFO2RO62L\\\"}\",\"notifyTime\":\"20200608143859\",\"notifyType\":2,\"signType\":\"RSA2\"},\"sign\":\"gV9P7jvBCX6qc8xRRfIAfi357Wpc9Rmt9MfOwa4QucRU3\\/WSf525WFlyfEt3BqeJ1qsDWGHLoH5l++77kUPL0qRcLc6InC6G1uxERaaJ8yPPZa9246mJJo3SyLr+3ZT5bq5mLYhOOx0Zj2GR0+08luLHB29f72xdXTNWvitro9o48O4RJ0CeldIG89J1N683ZXlRSIfUcIBoHWEUHlYV8sDZlQlAMcc9+dIjsg\\/5RGjkhJHI2LMKldJrvK8jCb1pruS+i4f51gatcdqwoDZ0zWy1xTaywPT4PdaBU6emB4Xn4kyW6NBCLFVEU0bIg9CNZXZ6TXSN5H6Kbl8ppJuKdA==\"}";
        Notify n = new Notify(str);
        System.out.printf("notify object: %s", n);
        assertNotNull(n);
        assertNotNull(n.response);
        assertNotNull(n.response.charset);
        assertNotNull(n.response.contentRaw);
        assertNotNull(n.response.content);
        assertNotNull(n.response.notifyTime);
        assertNotNull(n.response.signType);
        assertNotNull(n.response.notifyType);
        assertNotNull(n.response.content.notifyUrl);
        assertNotNull(n.response.content.orderSN);
        assertNotNull(n.response.content.resourceID);
        assertNotNull(n.response.content.createdAt);
        assertNotNull(n.response.content.pushType);
        assertNotNull(n.response.content.sendData);
    }

    @Test
    public void testShowSignUrl() throws Exception {
        GetShowSignUrlRequest req = new GetShowSignUrlRequest();
        GetShowSignUrlResponse resp = this.beforeParams.client.execute(req);
        assertEquals("10000", resp.code);
        assertEquals("请求成功", resp.message);
        assertEquals("", resp.subCode);
        assertEquals("", resp.subMsg);
        assertNotNull(resp.base64URL);
        assertNotNull(resp.url);
//        assertNotNull(resp.weChatH5URL);
        assertTrue(resp.verify());
    }

    @Test
    public void testGetUsersSignStatus() throws Exception {
        GetUserEsignStatusRequest req = new GetUserEsignStatusRequest();
        String phone = "13517210601";
        req.addPhone(phone);
        GetUserEsignStatusResponse resp = this.beforeParams.client.execute(req);
        assertEquals("10000", resp.code);
        assertEquals("请求成功", resp.message);
        assertEquals("", resp.subCode);
        assertEquals("", resp.subMsg);
        assertTrue(resp.verify());
        assertNotNull(resp.signUsers);
        assertEquals(1, resp.signUsers.size());
        assertEquals(phone, resp.signUsers.get(0).phone);
    }

    @Test
    public void testGetOneOrderByOuterOrderSN() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException, InvalidPrivateKeyException, InvalidPublicKeyException {
        List<BankPayOrder> orders = new ArrayList<>();
        String orderSN = UUID.randomUUID().toString().toUpperCase();
        orders.add(new BankPayOrder(
                orderSN,
                "12345678910",
                "123光",
                "1.00")
                .setIdentityCard("420222199212041057")
                .setNotifyUrl("http://127.0.0.1:7777")
        );
        PayBankRemitRequest payRequest = new PayBankRemitRequest()
                .setPayChannelK(GetBalanceRequest.BANK)
                .setOrders(orders);
//                                .setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD)
        DefaultCassPayClient newClient = new DefaultCassPayClient(this.beforeParams.url, this.beforeParams.APPID, this.beforeParams.privateKeyStr, this.beforeParams.vzhuoPublicKeyStr);
        newClient.setTimeout(TimeUnit.MILLISECONDS, 1, 1, 1, 1);
        String rbUUID;
        try {
            PayBankRemitResponse payResponse = newClient.execute(payRequest);
            assertEquals(payResponse.message + "/" + payResponse.subMsg, payResponse.code, "10000");
//            rbUUID = payResponse.rbUUID;
        } catch (RequestTimeoutException e) {

        } catch (Exception e) {
            throw e;
        }

        // 进行查询
        GetOneOrderByOuterOrderSNRequest request = new GetOneOrderByOuterOrderSNRequest();
        request.setOrderSN(orderSN);

        GetOneOrderByOuterOrderSNResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.message + "/" + response.subMsg, response.code, "60000");

        // 再次发起打款(同单号)
        PayBankRemitResponse payResponse2 = this.beforeParams.client.execute(payRequest);
        rbUUID = payResponse2.rbUUID;
        assertEquals(payResponse2.message + "/" + payResponse2.subMsg, payResponse2.code, "10000");
        assertTrue(payResponse2.isRequestSuccess());
        assertTrue(payResponse2.isSuccess());

        // 进行查询
        GetOneOrderByOuterOrderSNRequest request2 = new GetOneOrderByOuterOrderSNRequest();
        request2.setOrderSN(orderSN);

        GetOneOrderByOuterOrderSNResponse response2 = this.beforeParams.client.execute(request2);
        assertEquals(response2.message + "/" + response2.subMsg, response2.code, "10000");
        assertEquals(orderSN, response2.orderSN);
        assertNotNull(response2.orderUUID);
        assertNotNull(response2.reachAt);
        assertNotNull(response2.responseMsg);
        assertNotNull(response2.orderStatus);
        assertNotNull(response2.remitStatus);
    }

    // 测试同单号重复打款
    @Test
    public void testPayBankTwo() throws RequestTimeoutException, RequestFailedException, ResponseNotValidException, SignException {
        List<BankPayOrder> orders = new ArrayList<>();
        String orderSN = UUID.randomUUID().toString().toUpperCase();
        orders.add(new BankPayOrder(
                orderSN,
                "12345678910",
                "123光",
                "1.00")
                .setIdentityCard("420222199212041057")
                .setNotifyUrl("http://127.0.0.1:7777")
        );
        PayBankRemitRequest payRequest = new PayBankRemitRequest()
                .setPayChannelK(GetBalanceRequest.BANK)
                .setOrders(orders);

        PayBankRemitResponse response = this.beforeParams.client.execute(payRequest);
        assertTrue(response.isRequestSuccess());
        assertTrue(response.isSuccess());
        PayBankRemitResponse response2 = this.beforeParams.client.execute(payRequest);
        assertTrue(response2.isRequestSuccess());
        assertFalse(response2.isSuccess());
        assertEquals("50000", response2.code);
        assertEquals("api.common.error", response2.subCode);
        assertEquals("外部订单号不可重复", response2.subMsg);
        assertEquals("业务参数校验错误", response2.message);
    }
}
