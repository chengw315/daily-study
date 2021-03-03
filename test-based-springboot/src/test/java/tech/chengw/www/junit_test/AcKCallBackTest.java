package tech.chengw.www.junit_test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.rabbitmq.ack_callback.Provider;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/13
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class AcKCallBackTest {

    @MockBean
    private Provider provider;

    @Before
    public void init() {
        Mockito.when(provider.send("asd")).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        });
    }

    @Test
    public void testSend() {
        provider.send("hello");
    }
}
