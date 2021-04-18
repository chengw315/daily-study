package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/25
 **/
@Service
public class RedisTrx {
    @Autowired
    private RedisTemplate redisTemplate;

    public void trx() {
        String key = "test:trx";
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, "anyway");

        String s1 = (String) redisTemplate.opsForValue().get(key);

        //事务
        doTrx(key);
        doTrx1(key);

    }

    private void doTrx(String key) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(key);
                operations.multi();
                //WARNING Redis的事务中读操作返回的数据不能为写操作所用
                String s = (String) operations.opsForValue().get(key);
                //WARNING 实际写入nulls
                operations.opsForValue().set(key, s + "s");
                List exec = operations.exec();
                return null;
            }
        });
    }

    private void doTrx1(String key) {
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.watch(key);
        redisTemplate.multi();
        String s = (String) redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().set(key, s + "s");
        List exec = redisTemplate.exec();
        redisTemplate.setEnableTransactionSupport(false);
        System.out.println(exec);
    }

}
