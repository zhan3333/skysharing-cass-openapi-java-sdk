import com.skysharing.api.Constants;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.skysharing.api.request.GetBalanceRequest;
import com.skysharing.api.response.GetBalanceResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 余额获取相关测试
 */
public class TestGetBalance {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    /**
     * 获取银行通道余额
     *
     * @throws Exception e
     */
    @Test
    public void testGetBankBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_BANK);
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

    /**
     * 获取微信通道余额
     *
     * @throws Exception e
     */
    @Test
    @Ignore
    public void testGetWeChatBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_WECHAT);
        GetBalanceResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.wechat.lockedAmt);
        assertNotEquals("", response.wechat.canUseAmt);
        assertNotEquals("", response.wechat.childFAbalance);
        assertTrue(response.verify());
    }

    /**
     * 获取支付宝通道余额
     *
     * @throws Exception e
     */
    @Test
    public void testGetALiBalance() throws Exception {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setPayChannelK(Constants.PAY_CHANNEL_ALI);
        GetBalanceResponse response = this.beforeParams.client.execute(request);

        System.out.println(response);
        assertEquals("10000", response.code);
        assertEquals("请求成功", response.message);
        assertEquals("", response.subCode);
        assertEquals("", response.subMsg);
        assertNotEquals("", response.alipay.lockedAmt);
        assertNotEquals("", response.alipay.canUseAmt);
        assertNotEquals("", response.alipay.childFAbalance);
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
}
