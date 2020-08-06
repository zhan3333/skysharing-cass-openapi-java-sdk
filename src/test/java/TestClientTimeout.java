import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.*;
import com.skysharing.api.request.GetBalanceRequest;
import com.skysharing.api.response.GetBalanceResponse;
import okhttp3.OkHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestClientTimeout {

    private BeforeParams beforeParams;

    @Before
    public void init() throws InvalidPrivateKeyException, InvalidPublicKeyException {
        this.beforeParams = new BeforeParams();
    }

    // 设置极短的请求超时时间
    // 会抛出超时异常
    @Test(expected = RequestTimeoutException.class)
    public void TestTimeout() throws SignException, RequestFailedException, ResponseNotValidException, InvalidPrivateKeyException, InvalidPublicKeyException, RequestTimeoutException {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MILLISECONDS)
                .writeTimeout(1, TimeUnit.MILLISECONDS)
                .readTimeout(1, TimeUnit.MILLISECONDS)
                .build();
        this.beforeParams.client = new DefaultCassPayClient(this.beforeParams.url,
                this.beforeParams.APPID,
                this.beforeParams.privateKeyStr,
                this.beforeParams.vzhuoPublicKeyStr,
                okHttp
        );
        GetBalanceRequest req = new GetBalanceRequest();
        GetBalanceResponse resp = this.beforeParams.client.execute(req);
        System.out.print(resp);
    }

    // 设置比较长的超时时间
    // 这时不会抛出异常
    @Test
    public void TestNoTimeout() throws SignException, RequestFailedException, ResponseNotValidException, InvalidPrivateKeyException, InvalidPublicKeyException, RequestTimeoutException {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        this.beforeParams.client = new DefaultCassPayClient(this.beforeParams.url,
                this.beforeParams.APPID,
                this.beforeParams.privateKeyStr,
                this.beforeParams.vzhuoPublicKeyStr,
                okHttp
        );
        GetBalanceRequest req = new GetBalanceRequest();
        GetBalanceResponse resp = this.beforeParams.client.execute(req);
        System.out.print(resp);
    }

    @Test
    public void TestNoSetTimeout() throws SignException, RequestFailedException, ResponseNotValidException, InvalidPrivateKeyException, InvalidPublicKeyException, RequestTimeoutException {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .callTimeout(99999, TimeUnit.SECONDS)
                .connectTimeout(99999, TimeUnit.SECONDS)
                .readTimeout(99999, TimeUnit.SECONDS)
                .writeTimeout(99999, TimeUnit.SECONDS)
                .build();
        this.beforeParams.client = new DefaultCassPayClient(this.beforeParams.url,
                this.beforeParams.APPID,
                this.beforeParams.privateKeyStr,
                this.beforeParams.vzhuoPublicKeyStr,
                okHttp
        );
        GetBalanceRequest req = new GetBalanceRequest();
        GetBalanceResponse resp = this.beforeParams.client.execute(req);
        System.out.print(resp);
    }

    @Test(expected = RequestFailedException.class)
    public void TestServerGoAway() throws SignException, RequestFailedException, ResponseNotValidException, InvalidPrivateKeyException, InvalidPublicKeyException, RequestTimeoutException {
        this.beforeParams.client = new DefaultCassPayClient("http://no-exist.skysharing.cn/gateway/cass", this.beforeParams.APPID, this.beforeParams.privateKeyStr, this.beforeParams.vzhuoPublicKeyStr, "JSON", "RSA2");
        GetBalanceRequest req = new GetBalanceRequest();
        GetBalanceResponse resp = this.beforeParams.client.execute(req);
        System.out.print(resp);
    }
}
