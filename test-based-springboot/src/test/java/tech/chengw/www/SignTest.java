package tech.chengw.www;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/2
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SignTest {
    @Test
    public void httpInvoke() throws IOException, InterruptedException, CloneNotSupportedException {
        Long timestamp = System.currentTimeMillis();
        HttpPost httpPost = new HttpPost("http://localhost:8080/needSign");
        httpPost.addHeader("appId","chegnw");
        httpPost.addHeader("timestamp",timestamp.toString());

        HashMap<String, Object> body = new HashMap<>();
        body.put("imei","123456789");
        String stringBody = JSON.toJSONString(body);
        httpPost.addHeader("sign", DigestUtils.md5Hex(String.format("%s%s%s%s",
                "chengw", "junweicheng", stringBody, timestamp)));
        httpPost.setEntity(new StringEntity(stringBody));


        HashMap<String, Object> fakeBody = new HashMap<>(body);
        fakeBody.put("imei","78423542");
        HttpPost fakePost = (HttpPost) httpPost.clone();
        fakePost.setEntity(new StringEntity(JSON.toJSONString(fakeBody)));

        //正常请求
        HttpClients.createDefault().execute(httpPost);
        //伪造请求攻击
        HttpClients.createDefault().execute(fakePost);
        for (int i = 0; i < 30; i++) {
            //重放攻击
            HttpClients.createDefault().execute(httpPost);
            Thread.sleep(1000);
        }
    }
}
