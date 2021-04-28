import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.*;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.model.RemitOrder;
import com.skysharing.api.request.*;
import com.skysharing.api.response.GetOneOrderByOuterOrderSNResponse;
import com.skysharing.api.response.GetOneOrderStatusResponse;
import com.skysharing.api.response.GetOneRemitStatusResponse;
import com.skysharing.api.response.PayBankRemitResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * 测试订单查询相关
 */
@Ignore
public class TestQueryOrder {
    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
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
    @Ignore
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

}
