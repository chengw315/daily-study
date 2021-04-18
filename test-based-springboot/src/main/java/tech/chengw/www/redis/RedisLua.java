package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/18
 **/
@Component
public class RedisLua {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final int maxFailNum = 7;
    /**
     * Lua脚本 hash变量操作
     */
    public void luaHash() {
        ArrayList<String> keys = new ArrayList<>();
        String key = "fail_flag_1";
        String hashKey = "fail_flag_2";
        keys.add(key);
        keys.add(hashKey);
        String value = "value";
        // 哈希表指定键下有值，删除整个哈希表，返回true。否则在哈希表此键下添加值
        final String lua = "if (redis.call('HEXISTS', KEYS[1], KEYS[2]) > 0) then redis.call('DEL', KEYS[1]) return true else redis.call('HSET', KEYS[1], KEYS[2], ARGV[1]) return false end";
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(lua,Boolean.class);
        Boolean execute = (Boolean) redisTemplate.execute(script, keys, value);
        Boolean execute2 = (Boolean) redisTemplate.execute(script, keys, value);
    }

    /**
     * Lua脚本，原子减
     */
    public boolean lua() {
        //用户可用设备量 > 0，则减1，返回true，否则返回false
        final String lua = "local num = tonumber(redis.call('get', KEYS[1])) if (num > 0) then redis.call('set', KEYS[1], num - 1) return true else return false end";
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(lua,Boolean.class);

        String key = "USER:" + "${openId}";
        ArrayList<String> keys = new ArrayList<>();
        keys.add(key);

        return  (Boolean) redisTemplate.execute(script, keys);
    }

    /**
     * Lua脚本，尝试原子减
     */
    public int tryReduceUserNodesNum(String openId, int dlt){
        String key = "USER:" + "${openId}";
        //用户可用设备量 > 0，则减1，返回true，否则返回false
        final String lua = "local num = tonumber(redis.call('get', KEYS[1])) - ARGV[1] if (num >= 0) then redis.call('set', KEYS[1], num) return 0 else return num end";
        DefaultRedisScript<Long> script = new DefaultRedisScript<>(lua,Long.class);

        ArrayList<String> keys = new ArrayList<>();
        keys.add(key);

        return ((Long)redisTemplate.execute(script, keys, dlt)).intValue();
    }

    /**
     * 添加处理失败的同步消息，达到最大失败次数后删除
     * @param tag
     * @return true-未达到最大失败次数，false-达到最大失败次数
     */
    public boolean tryAddSyncFailMessage(long tag) {
        String key = "X:FAIL";
        //用户可用设备量 > 0，则减1，返回true，否则返回false
        final String lua = "local num = tonumber(redis.call('HGET',KEYS[1] ,KEYS[2])) if(num==nil) then num=0 end if (num >= ARGV[1] - 1) then redis.call('HDEL',KEYS[1],KEYS[2]) return false else redis.call('HSET',KEYS[1],KEYS[2],num+1) return true end";
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>(lua, Boolean.class);

        ArrayList<Object> keys = new ArrayList<>();
        keys.add(key);
        keys.add(tag);

        try {
            return (Boolean) redisTemplate.execute(script, keys, maxFailNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加处理失败的同步消息，达到最大失败次数后删除
     * @param tag
     * @return true-未达到最大失败次数，false-达到最大失败次数
     */
    public void deleteSyncFailMessage(long tag) {
        redisTemplate.opsForHash().delete("X:FAIL",tag);
    }
}
