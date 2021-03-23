package tech.chengw.www.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/23
 **/
@Service
public class RedissonDemo {
    @Autowired
    private RedissonClient redissonClient;
    /**
     * 公平锁事件驱动（subpub特性）
     */
    RLock fairLock = redissonClient.getFairLock(this.getClass().getName());

    public void lock() {
        fairLock.lock(10, TimeUnit.SECONDS);
    }
    public void unlock() {
        fairLock.unlock();
    }
}
