package tech.chengw.www.rabbitmq.ack_callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description 使用ReturnCallback机制的发信方
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/12
 **/
@Component
public class Provider {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public String send(String message) {
        rabbitTemplate.convertAndSend("exchange_test_ack_callback","*",message,new CorrelationData());
        return null;
    }
}
