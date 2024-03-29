import com.alibaba.fastjson.JSON;
import com.skysharing.api.Constants;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.model.AliPayOrder;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.request.PayAliRemitRequest;
import com.skysharing.api.request.PayOneAliRemitRequest;
import com.skysharing.api.response.PayAliRemitResponse;
import com.skysharing.api.response.PayOneAliRemitResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * 支付宝支付相关
 */
@Ignore
public class TestALiPay {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    /**
     * 测试支付宝单笔支付
     *
     * @throws Exception 异常
     */
    @Test
    public void payOneAli() throws Exception {
        PayOneAliRemitRequest request = new PayOneAliRemitRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_ALI);
        request.setOrder(
                new AliPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
                .setPhone("13517210601")
        );

        PayOneAliRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    /**
     * 测试支付宝单笔支付到银行卡
     *
     * @throws Exception 异常
     */
    @Test
    public void payOneAliToBank() throws Exception {
        PayOneAliRemitRequest request = new PayOneAliRemitRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_ALI);
        request.setPayeeChannelType(Constants.PAYEE_CHANNEL_BANK);
        request.setOrder(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "6214850278508756",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
                        .setPhone("13517210601")
        );

        PayOneAliRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    /**
     * 测试支付宝批量支付
     *
     * @throws Exception 异常
     */
    @Test
    public void payBatchAli() throws Exception {
        PayAliRemitRequest request = new PayAliRemitRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_ALI);
        List<AliPayOrder> orders = new ArrayList<>();

        orders.add(
                new AliPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
        );
        orders.add(
                new AliPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "13517210601",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        PayAliRemitResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }

    /**
     * 测试支付宝批量支付到银行卡
     *
     * @throws Exception 异常
     */
    @Test
    public void payBatchAliToBank() throws Exception {
        PayAliRemitRequest request = new PayAliRemitRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_ALI);
        request.setPayeeChannelType(Constants.PAYEE_CHANNEL_BANK);
        List<BankPayOrder> orders = new ArrayList<>();

        orders.add(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "6214850278508756",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
        );
        orders.add(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "6214850278508756",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setBankOrders(orders);
        PayAliRemitResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }
}
