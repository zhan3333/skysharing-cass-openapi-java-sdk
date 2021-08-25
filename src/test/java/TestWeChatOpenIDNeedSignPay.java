import com.alibaba.fastjson.JSON;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.model.WeChatOpenIDOrder;
import com.skysharing.api.request.SingleAuthorizationOneWeChatRemitRequest;
import com.skysharing.api.request.SingleAuthorizationWeChatRemitRequest;
import com.skysharing.api.response.SingleAuthorizationOneWeChatRemitResponse;
import com.skysharing.api.response.SingleAuthorizationWeChatRemitResponse;
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
public class TestWeChatOpenIDNeedSignPay {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    @Test
    public void testWeChatRemit() throws Exception {
        SingleAuthorizationWeChatRemitRequest request = new SingleAuthorizationWeChatRemitRequest();
        List<WeChatOpenIDOrder> orders = new ArrayList<>();

        orders.add(
                (new WeChatOpenIDOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "oFltO5e749REB4sFx8zBIls4FQCc",
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
        SingleAuthorizationWeChatRemitResponse response = this.beforeParams.client.execute(request);

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
        SingleAuthorizationOneWeChatRemitRequest request = new SingleAuthorizationOneWeChatRemitRequest();
        request.setOrder(
                new WeChatOpenIDOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "oFltO5e749REB4sFx8zBIls4FQCc",
                        "1",
                        "https://www.baidu.com"
                )
                        .setIdentityCard("421221199309035723")
                        .setPayeeAccount("许文婷")
        );

        SingleAuthorizationOneWeChatRemitResponse response = this.beforeParams.client.execute(request);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotNull(response.rbUUID);
        assertTrue(response.verify());
    }
}
