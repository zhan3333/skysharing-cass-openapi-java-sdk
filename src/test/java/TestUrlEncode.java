import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Ignore
public class TestUrlEncode {
    @Test
    public void test() throws UnsupportedEncodingException {
        String str = "$-_.+!*'(),";
        String newStr = URLEncoder.encode(str, "UTF-8");
        System.out.println(newStr);
    }
}
