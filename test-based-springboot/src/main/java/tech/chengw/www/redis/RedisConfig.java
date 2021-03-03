package tech.chengw.www.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/23
 **/
@Configuration
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //redisTemplate.setEnableTransactionSupport(true);
        RedisSerializer mySerializer = new RedisSerializer() {
            private StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
            @Override
            public byte[] serialize(Object o) throws SerializationException {
                if (o instanceof String) {
                    return ((String) o).getBytes();
                }
                return JSON.toJSONBytes(o);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                return stringRedisSerializer.deserialize(bytes);
            }
        };
        redisTemplate.setKeySerializer(mySerializer);
        redisTemplate.setHashKeySerializer(mySerializer);

        redisTemplate.setHashValueSerializer(mySerializer);
        redisTemplate.setValueSerializer(mySerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;

    }
}
