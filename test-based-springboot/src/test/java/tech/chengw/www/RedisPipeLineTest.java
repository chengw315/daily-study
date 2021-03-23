package tech.chengw.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.redis.RedisPipeLine;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/18
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisPipeLineTest {
    @Autowired
    private RedisPipeLine redisPipeLine;

    @Test
    public void testPipeLine() {
        redisPipeLine.pipeLine();
    }

}
