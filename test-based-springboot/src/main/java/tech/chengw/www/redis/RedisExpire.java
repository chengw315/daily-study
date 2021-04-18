package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/25
 **/
@Service
public class RedisExpire {

    @Autowired
    private RedisTemplate redisTemplate;

    public void expire() {
        //设置过期时间
        redisTemplate.opsForValue().set("key",20,10000, TimeUnit.MILLISECONDS);
        //刷新过期时间
        redisTemplate.expire("key",10000,TimeUnit.MILLISECONDS);
        //重设过期时间
        redisTemplate.opsForValue().set("key",20);
        //不影响过期时间
        redisTemplate.opsForValue().increment("key",1);
    }
}
