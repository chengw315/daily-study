package tech.chengw.www;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.Lifecycle;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/23
 **/
@SpringBootApplication
public class MainApplication implements ApplicationContextAware, Lifecycle {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MainApplication.applicationContext = applicationContext;
    }
    static ApplicationContext applicationContext;
    public static <T>T getBean(Class<T> aClass) {
        return applicationContext.getBean(aClass);
    }

    @Override
    public void start() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    rabbitTemplate.convertAndSend("exchange_test_ack_callback", " ", "xxxx");
                }
            }).start();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
