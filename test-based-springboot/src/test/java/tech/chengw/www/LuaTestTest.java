package tech.chengw.www;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.redis.lua.LuaTest;

import java.util.ArrayList;
import java.util.Arrays;
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
@RunWith(SpringRunner.class)
public class LuaTestTest {

    @Autowired
    private LuaTest luaTest;
    @Autowired
    private RedisTemplate redisTemplate;
    private final String key = "USER:" + "${openId}";

    @Before
    public void init() {
        redisTemplate.opsForValue().set(key,100);
    }

    @Test
    public void testLua() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CountDownLatch countDownLatch = new CountDownLatch(200);
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute(() -> {
                if (luaTest.lua()) {
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
        luaTest.deleteSyncFailMessage(tag);
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        int time = 10;
        CountDownLatch countDownLatch = new CountDownLatch(time);
        for (int i = 0; i < time; i++) {
            threadPoolExecutor.execute(() -> {
                if (luaTest.tryAddSyncFailMessage(tag)) {
                    atomicInteger.incrementAndGet();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        assert atomicInteger.get() == time -  time / LuaTest.maxFailNum;
    }
}
