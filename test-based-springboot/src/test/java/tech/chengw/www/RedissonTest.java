package tech.chengw.www;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.chengw.www.redis.RedissonDemo;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/4/1
 **/
@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonDemo redissonDemo;

    @Test
    public void test() throws InterruptedException {
        redissonDemo.lock();
        System.out.println("master thread lock");
        new Thread(() -> {
            redissonDemo.lock();
            System.out.println("slave thread lock");
            redissonDemo.unlock();
            System.out.println("slave thread unlock");
        }).start();
        Thread.sleep(1000);
        redissonDemo.unlock();
        System.out.println("master thread unlock");
        Thread.sleep(1000);
    }
}
