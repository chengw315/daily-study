package tech.chengw.www;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.redis.RedisLua;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/23
 **/
@SpringBootTest
public class RedisLuaTest {

    @Autowired
    private RedisLua redisLua;
    @Autowired
    private RedisTemplate redisTemplate;
    private final String key = "USER:" + "${openId}";

    @BeforeAll
    public void init() {
        redisTemplate.opsForValue().set(key, 100);
    }

    @Test
    public void testLua() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute(() -> {
                if (redisLua.lua()) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        assert atomicInteger.get() == 100;
    }

    @Test
    public void testTryAddSyncFailMessage() throws InterruptedException {
        long tag = 100000000;
        redisLua.deleteSyncFailMessage(tag);
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        int time = 10;
        CountDownLatch countDownLatch = new CountDownLatch(time);
        for (int i = 0; i < time; i++) {
            threadPoolExecutor.execute(() -> {
                if (redisLua.tryAddSyncFailMessage(tag)) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        assert atomicInteger.get() == time - time / RedisLua.maxFailNum;
    }

    @Test
    public void testIncrement() {
        redisTemplate.opsForHash().increment("zzz", "zzz", 1);
    }
}
