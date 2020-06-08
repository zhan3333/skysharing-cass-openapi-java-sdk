package notifyreceiveserver;

import com.skysharing.api.DefaultCassPayClient;
import com.skysharing.api.exception.InvalidPrivateKeyException;
import com.skysharing.api.exception.InvalidPublicKeyException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(7777), 0);
        server.createContext("/", new TestHandler());
        System.out.println("Start server :7777");
        server.start();
    }

    static class TestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Dotenv dotenv = Dotenv.load();

            String postStr = IOUtils.toString(exchange.getRequestBody(), StandardCharsets.UTF_8);
            Map<String, String> map = new HashMap<>();
            try {
                DefaultCassPayClient client = new DefaultCassPayClient(dotenv.get("API_URL"), dotenv.get("APPID"), dotenv.get("PRIVATE_KEY_STR"), dotenv.get("VZHUO_PUBLIC_KEY_STR"));
                client.setDebug(true);
                boolean ret = client.verifyNotify(postStr);
                System.out.printf("response verify: %b", ret);
                if (ret) {
                    map.put("content", "success");
                } else {
                    map.put("content", "failed");
                }
            } catch (InvalidPrivateKeyException | InvalidPublicKeyException e) {
                e.printStackTrace();
            }

            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(map.toString().getBytes());
            os.close();
        }
    }
}