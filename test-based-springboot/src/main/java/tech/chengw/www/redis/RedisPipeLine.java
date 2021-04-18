package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/18
 **/
@Service
public class RedisPipeLine {
    @Autowired
    private RedisTemplate redisTemplate;

    public void pipeLine() {
        String key = "listTest";
        redisTemplate.delete(key);
        List listTest = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            for (int i = 0; i < 20; i++) {
                //写操作
                redisTemplate.opsForList().leftPush(key, i);
//                if (i > 10) {
//                    //WARNING 读操作会立即触发服务器reply，使得管道优势无存，如果读操作较多，不适合使用管道
//                    Object leftPop = redisTemplate.opsForList().leftPop(key);
//                    System.out.println(leftPop);
//                }
            }
            connection.closePipeline();
            return null;
        });
        System.out.println(listTest);
    }
}
