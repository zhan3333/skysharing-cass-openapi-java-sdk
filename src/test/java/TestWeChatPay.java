import com.alibaba.fastjson.JSON;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.request.PayOneWeChatRemitRequest;
import com.skysharing.api.request.PayWeChatRemitRequest;
import com.skysharing.api.response.PayOneWeChatRemitResponse;
import com.skysharing.api.response.PayWeChatRemitResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * 测试微信付款相关
 */
@Ignore
public class TestWeChatPay {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
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
}
