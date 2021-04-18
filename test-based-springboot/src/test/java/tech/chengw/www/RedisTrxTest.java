package tech.chengw.www;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.chengw.www.redis.RedisTrx;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/25
 **/
@SpringBootTest
public class RedisTrxTest {
    @Autowired
    private RedisTrx redisTrx;

    @Test
    public void testTrx() {
        redisTrx.trx();
    }
}
