package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/26
 **/
@Service
public class RedisScan {

    @Autowired
    private RedisTemplate redisTemplate;

    public void scan() {
        String key = "test:scan";
        //插数据
        intiData(key);

        Cursor<List<Map.Entry<String,String>>> cursor = redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions()
                .count(100).match("hkey*").build());
        while (cursor.hasNext()) {
            List<Map.Entry<String, String>> next = cursor.next();
            System.out.println(next);
        }
    }

    /**
     * 初始数据
     * @param key
     */
    private void intiData(String key) {
        redisTemplate.delete(key);
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            for (int i = 0; i < 2000; i++) {
                redisTemplate.opsForHash().put(key, "hkey-"+i, i);
            }
            connection.closePipeline();
            return null;
        });
    }

}
