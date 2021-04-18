package tech.chengw.www;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import tech.chengw.www.junit_test.Nodes4Redis;
import tech.chengw.www.redis.RedisPipeLine;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/18
 **/
@SpringBootTest
public class RedisPipeLineTest {
    @Autowired
    private RedisPipeLine redisPipeLine;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPipeLine() {
        redisPipeLine.pipeLine();
    }

    @Test
    public void add1wNode() {
        String openId = "00000000";
        String productId = "00000000";
        String prefix = "XIOT:CMP:CMP_NODE" + ":";
        String value = JSON.toJSONString(new Nodes4Redis()
                .setAvailable(true)
                .setConnected(true)
                .setClientId("00000000000000")
                .setDocumentId(openId + "-" + productId + "-" + "00000")
                .setDeviceSecret("0000000000000")
                .setSessionKey("00000000000000")
                .setSessionSalt("0000000000000000")
                .setCheckSession(true));
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            for (int i = 0; i < 10000; i++) {
//                redisTemplate.opsForHash().put(RedisSyncService.REDIS_KEY_NODE,openId + "-" + productId + "-" + nodeEui,
                redisTemplate.opsForValue().set(i, value);
            }
            connection.closePipeline();
            return null;
        });
    }

}
