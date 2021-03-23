package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * description 发布订阅测试
 *  ！！！同一个消息会到达两个相同的订阅者和一个模糊订阅者，说明redis的发布订阅模式属于广播
 *  根据时间复杂度可以推测：模糊订阅应该使用了字符串字典算法，可在O(n) 时间内插入一个pattern或查询所有符合的pattern。（n：pattern数）
 * @author chengwj
 * @version 1.0
 * @date 2021/3/18
 **/
@Service
public class RedisPubSub {

    @Autowired
    private RedisTemplate redisTemplate;
    private String channel = "topic.B.C";
    private String channelFuzzy = "topic.B.*";

    /**
     * 定阅与监听 监听1个——O(1)
     */
    public void subscript() {
        redisTemplate.getConnectionFactory().getConnection().subscribe((message, pattern) -> {
            System.out.print("subscribe get!");
            System.out.print("message:");
            System.out.print(message);
            System.out.print(" pattern:");
            System.out.println(pattern);
        },channel.getBytes());
    }

    /**
     * 订阅与监听（模糊订阅），监听1个——O(1+M) M：服务器已有pattern数
     */
    public void pSubscript() {
        redisTemplate.getConnectionFactory().getConnection().pSubscribe((message, pattern) -> {
            System.out.print("pSubscribe get!");
            System.out.print("message:");
            System.out.print(message);
            System.out.print(" pattern:");
            System.out.println(new String(pattern));
        },channelFuzzy.getBytes());
    }

    /**
     * 发布 O(N+M) N：订阅数，M：服务器又有pattern数
     */
    public void publish() {
        redisTemplate.getConnectionFactory().getConnection().publish(channel.getBytes(),"message".getBytes());
    }


    /**
     * 定阅与监听（重复订阅的情况）
     */
    public void subscript2() {
        redisTemplate.getConnectionFactory().getConnection().subscribe((message, pattern) -> {
            System.out.print("subscribe2 get!");
            System.out.print("message:");
            System.out.print(message);
            System.out.print(" pattern:");
            System.out.println(pattern);
        },channel.getBytes());
    }
}
