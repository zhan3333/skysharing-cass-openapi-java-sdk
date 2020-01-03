import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.RequestFailedException;
import com.skysharing.api.exception.ResponseNotValidException;
import com.skysharing.api.exception.SignException;
import com.skysharing.api.model.BankPayOrder;
import com.skysharing.api.request.GetBalanceRequest;
import com.skysharing.api.request.PayOneBankRemitRequest;
import com.skysharing.api.response.PayOneBankRemitResponse;

import java.util.UUID;

public class OneBankRemitThread implements Runnable {
    private final DefaultCassPayClient client;
    private final Integer index;
    private final PayOneBankRemitRequest request;

    public OneBankRemitThread(DefaultCassPayClient client, Integer index) {
        this.client = client;
        this.index = index;
        this.request = new PayOneBankRemitRequest();
        this.request.setPayChannelK(GetBalanceRequest.BANK);
    }

    @Override
    public void run() {
        System.out.println("Index " + this.index + " start");
        this.request.setOrder(
                new BankPayOrder(
                        UUID.randomUUID().toString().toUpperCase(),
                        "1111111_" + this.index,
                        "詹光",
                        "0.01"
                ).setIdentityCard("420222199212041058")
        );
        PayOneBankRemitResponse response = null;
        try {
            response = this.client.execute(this.request);
            System.out.println("Index " + this.index + " end: " + response.rbUUID);
        } catch (ResponseNotValidException | SignException | RequestFailedException e) {
            System.out.println("Index " + this.index + " error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


