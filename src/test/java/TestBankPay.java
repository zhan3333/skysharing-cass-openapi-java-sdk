import com.alibaba.fastjson.JSON;
import com.skysharing.api.exception.*;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.request.GetBalanceRequest;
import com.skysharing.api.request.PayBankRemitRequest;
import com.skysharing.api.request.PayOneBankRemitRequest;
import com.skysharing.api.response.PayBankRemitResponse;
import com.skysharing.api.response.PayOneBankRemitResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * 银行卡付款相关测试
 */
@Ignore
public class TestBankPay {

    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
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
                        "15827637967",
                        "许文婷1",
                        "0.1"
                )
                        .setIdentityCard("421221199309035723")
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
    // 测试银行卡支付
    public void testPayBankRemit() throws Exception {
        PayBankRemitRequest request = new PayBankRemitRequest();
        request.setPayChannelK(GetBalanceRequest.BANK);
        List<BankPayOrder> orders = new ArrayList<>();

        orders.add(new BankPayOrder(
                UUID.randomUUID().toString().toUpperCase(),
                "6214850271449677",
                "谢丽1",
                "0.01")
                .setIdentityCard("421221199309035723")
                .setNotifyUrl("http://127.0.0.1:7777")
                .setPhone("15827637967")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        request.setContractID("13");
        request.setPayeeChannelType(PayBankRemitRequest.PAYEE_CHANNEL_TYPE_BANK_CARD);
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
        request.setPayeeChannelType(PayOneBankRemitRequest.PAYEE_CHANNEL_TYPE_ALI_PAY);
        request.setOrder(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "15827637967",
                        "许文婷",
                        "0.1"
                )
                        .setIdentityCard("420222199212041058")
                        .setNotifyUrl("http://www.baidu.com")
                        .setPhone("15827637967")
        );
//        request.setContractID("12");
        PayOneBankRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals(response.toString(), "10000", response.code);
        assertNotNull(response.toString(), response.rbUUID);
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

    // 测试同单号重复打款
    @Test
    public void testPayBankTwo() throws RequestTimeoutException, RequestFailedException, ResponseNotValidException, SignException, InterruptedException {
        List<BankPayOrder> orders = new ArrayList<>();
        String orderSN = UUID.randomUUID().toString().toUpperCase();
        orders.add(new BankPayOrder(
                orderSN,
                "12345678910",
                "123光",
                "1.00")
                .setIdentityCard("421221199309035723")
                .setNotifyUrl("http://127.0.0.1:7777")
        );
        PayBankRemitRequest payRequest = new PayBankRemitRequest()
                .setPayChannelK(GetBalanceRequest.BANK)
                .setOrders(orders);

        PayBankRemitResponse response = this.beforeParams.client.execute(payRequest);
        assertTrue(response.isRequestSuccess());
        assertTrue(response.isSuccess());
        TimeUnit.SECONDS.sleep(1);
        PayBankRemitResponse response2 = this.beforeParams.client.execute(payRequest);
        assertTrue(response2.isRequestSuccess());
        assertFalse(response2.isSuccess());
        assertEquals("50000", response2.code);
        assertEquals("api.common.error", response2.subCode);
        assertEquals("外部订单号不可重复", response2.subMsg);
        assertEquals("业务参数校验错误", response2.message);
    }
}
