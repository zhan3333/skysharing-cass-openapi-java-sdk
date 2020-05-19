package notifyreceiveserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skysharing.api.Signer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.skysharing.api.Signer.SIGNATURE_ALGORITHM;


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
            String postStr = IOUtils.toString(exchange.getRequestBody(), StandardCharsets.UTF_8);
            Signer signer = new Signer();
            JSONObject json = JSON.parseObject(postStr);
            System.out.println("raw: " + json);
            String sign = json.getString("sign");
            JSONObject req = json.getJSONObject("response");
            System.out.println("req: " + req.toString());
            System.out.println("sign: " + sign);

            // 去除空值

            JSONObject filteredParams = new JSONObject();
            for (String key : req.keySet()) {
                if (req.getString(key).equals("") || req.getString(key).equals("{}") || req.getString(key).equals("[]")) {
                    break;
                }
                filteredParams.put(key, req.get(key));
            }

            // 排序 + json encode
            String waitSignStr = JSON.toJSONString(filteredParams, SerializerFeature.MapSortField, SerializerFeature.WriteSlashAsSpecial);
            System.out.println("Sort + to json: " + waitSignStr);

            // 替换空字符串
            waitSignStr = waitSignStr.replace(" ", "");
            System.out.println("Replace space: " + waitSignStr);

            // url encode
            waitSignStr = URLEncoder.encode(waitSignStr, "UTF-8");
            System.out.println("Url encode: " + waitSignStr);

            try {
                // 验证

                // 创建验证对象
                Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
                Dotenv dotenv = Dotenv.load();
                PublicKey publicKey = signer.getPublicKey(dotenv.get("VZHUO_PUBLIC_KEY_STR"));
                signature.initVerify(publicKey);
                signature.update(waitSignStr.getBytes(StandardCharsets.UTF_8));
                // 执行验证
                boolean verify = signature.verify(Base64.getDecoder().decode(sign));
                // 输出验证结果
                System.out.println("验证结果: " + verify);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Map<String, String> map = new HashMap<>();
            map.put("content", "success");
            String response = "hello world";
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(map.toString().getBytes());
            os.close();
        }
    }
}