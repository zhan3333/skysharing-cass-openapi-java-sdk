import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.*;
import com.skysharing.api.model.Notify;
import com.skysharing.api.request.GetShowSignUrlRequest;
import com.skysharing.api.request.GetUserEsignStatusRequest;
import com.skysharing.api.request.GetUsersVerifyStatusRequest;
import com.skysharing.api.request.VerifyUserRequest;
import com.skysharing.api.response.GetShowSignUrlResponse;
import com.skysharing.api.response.GetUserEsignStatusResponse;
import com.skysharing.api.response.GetUsersVerifyStatusResponse;
import com.skysharing.api.response.VerifyUserResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static org.junit.Assert.*;

@Ignore
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

    // 3.13 查询认证状态
    @Ignore
    @Test
    public void testGetUsersVerifyStatus() throws ResponseNotValidException, RequestFailedException, SignException, RequestTimeoutException {
        GetUsersVerifyStatusRequest request = new GetUsersVerifyStatusRequest();

        request.addItem(new GetUsersVerifyStatusRequest.Item("421221199309035723", "429006199112162721", 1));

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
                .setPhone("15827637967");
        VerifyUserResponse response = this.beforeParams.client.execute(request);
        System.out.println(response);
        assertEquals(response.message + "/" + response.subMsg, "10000", response.code);
    }

    @Test
    public void testGetUsersSignStatus() throws Exception {
        GetUserEsignStatusRequest req = new GetUserEsignStatusRequest();
        String phone = "15827637967";
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


}
