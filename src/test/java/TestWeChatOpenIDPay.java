import com.alibaba.fastjson.JSON;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.model.WeChatOrder;
import com.skysharing.api.request.PayOneWeChatOpenIDRemitRequest;
import com.skysharing.api.request.PayOneWeChatRemitRequest;
import com.skysharing.api.request.PayWeChatOpenIDRemitRequest;
import com.skysharing.api.request.PayWeChatRemitRequest;
import com.skysharing.api.response.PayOneWeChatOpenIDRemitResponse;
import com.skysharing.api.response.PayOneWeChatRemitResponse;
import com.skysharing.api.response.PayWeChatOpenIDRemitResponse;
import com.skysharing.api.response.PayWeChatRemitResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * 测试微信付款到 OpenID 相关
 */
@Ignore
public class TestWeChatOpenIDPay {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    @Test
    public void testWeChatRemit() throws Exception {
        PayWeChatOpenIDRemitRequest request = new PayWeChatOpenIDRemitRequest();
        List<WeChatOpenIDOrder> orders = new ArrayList<>();

        orders.add(
                (new WeChatOpenIDOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "oFltO5fE7Z6vzfkGO0anH_4eySMk",
                        "1",
                        "https://www.baidu.com")
                )
                        .setPhone("15827637967")
                        .setIdentityCard("421221199309035723")
                        .setTax("0.01")
                        .setPayeeAccount("许文婷")
        );
        System.out.println(JSON.toJSONString(orders));
        request.setOrders(orders);
        PayWeChatOpenIDRemitResponse response = this.beforeParams.client.execute(request);

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
        PayOneWeChatOpenIDRemitRequest request = new PayOneWeChatOpenIDRemitRequest();
        request.setOrder(
                new WeChatOpenIDOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "oFltO5fE7Z6vzfkGO0anH_4eySMk",
                        "1",
                        "https://www.baidu.com"
                )
        );

        PayOneWeChatOpenIDRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }
}
