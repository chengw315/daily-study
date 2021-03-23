package tech.chengw.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.redis.RedisPubSub;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisPubSubTest {
    @Autowired
    private RedisPubSub redisPubSub;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPubSub() throws InterruptedException {
        redisPubSub.pSubscript();
        redisPubSub.subscript();
        redisPubSub.subscript2();
        for (int i = 0; i < 10; i++) {
            redisPubSub.publish();
            //client处于PubSub状态时，能正常操作redis
            redisTemplate.opsForList().leftPush("any","any");
            Thread.sleep(1000);
        }
    }

}
