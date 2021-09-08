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
                        "15827637967",
                        "许文婷",
                        "0.01"
                ).setNotifyUrl("http://www.baidu.com")
                        .setPhone("15827637967")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
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
                        "6230580000231795720",
                        "许文婷",
                        "0.01"
                )
                        .setNotifyUrl("http://www.baidu.com")
                        .setPhone("15827637967")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
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
                        "15827637967",
                        "许文婷",
                        "0.01"
                )
                        .setNotifyUrl("http://www.baidu.com")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
        );
        orders.add(
                new AliPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "15827637967",
                        "许文婷",
                        "0.01"
                )
                        .setNotifyUrl("http://www.baidu.com")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
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
                        "6230580000231795720",
                        "许文婷",
                        "0.01"
                )
                        .setNotifyUrl("http://www.baidu.com")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
        );
        orders.add(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "6230580000231795720",
                        "许文婷",
                        "0.01"
                )
                        .setNotifyUrl("http://www.baidu.com")
                        .setIDCardFrontImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5\n" +
                                "55 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setIDCardBackImg("data:image/png;base64,iVBORw 0KGgoAAAANSUhEUgAAAQAA AAEAAQMAAABmvDolAAAABlB MVEX///8AAABVwtN+AAACW0l EQVR42uyYO47jPBCES2DAkEfg UXixH3rAF+NReASGDAjVj2rNW\n" +
                                "B7sTDzkrjswZPlL2v2qbrztbf+kL SR5ejp96EUClpr0Nk8ENACrz64 CYDH+qAlAmAqIrCsSebSEuIdsg GcfDvDZ8ZQXK0ByUkB5tMnNo 0Iv5gOUUUAPR4PihBZ3/JFyvw xcxZsFJFfWkFs8vqnusYHLrPv4j Lj/0E4HB5YWe2C7vriyVEAZhfh 4NtIRALAuzPbns6yBvizMLT4q5 gFkgS11jSsnzALi2TERYMHafW ZdrC4+uiniVIBKIpw+W4mTNtRI+ rJ9VvcIgIbUDvSrGe1Qt5c95gJi D6bSLAKn/N6RfNmedTED4AvC wWxuAls1KUQW94zm7wMLS8 dGOvUhPWWvtG93XcwAANFZ RoXTckseqiTiPXknAerWLhVkbgJ rYIuPgYBr4yARdk+1SClLjd/nUJs BsL6+afKq5dsGpTBYS50JsDglK KN6PG3j4lfR9vuAhIBFYEVyBUg 0kfOqNccHlganbbbfbpaNfBU5U wDRrgfWTRFP6YdTqnOr4wCffd L27q6Mv+TBPdSmAHyhfnPVos KKpm3WF3fLg/EBC5b2QEljp2Q Clvp1GRwfUI/C9lEX2lCyt8vZq2 weATAvHM2L1eRBhS//hYmAzyt rD6TFIrMsNd0byhzAdWUlr9Mfg AZz82UcDADYZc+ekp0/Wjxsjar TAXZl9ezxYPY/nmHHB/rHsiU3tZ hzBwYC7MqaDLDpZRsrX/XDBM Dzynoi9XgG2i7lvznDDgy87W1/ 56\n" +
                                "57 mf0fAAD//8XcEC/1YcATAAAAA ElFTkSuQmCC")
                        .setThirdPartyUserID("202109012312")
                        .setSignUpAt("2021-09-01 12:12:12")
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
