package tech.chengw.www.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *   1. 创建客户端，使用用户名密码建立客户端
 *   2. 创建交换机、队列
 *   3. 使用客户端发信       （自行实现）
 *   4. 创建消费者消费消息   （自行实现）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/12
 **/
@Configuration
public class RabbitConfig {

    /**
     * —————————————————————————————————————————建立客户端————————————————————————————————————————————————
     * 1. 自动生成的客户端，使用了spring-integration-amqp就会自动生成 ConnectionFactory和RabbitTemplate
     * 2. 需要自定义内容的客户端
     */

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("rpc_reply");
        return container;
    }
    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate, SimpleMessageListenerContainer container) {
        return new AsyncRabbitTemplate(rabbitTemplate,container);
    }

    /**
     * —————————————————————————————————————————交换机、队列、绑定关系——————————————————————————————————————
     */

    @Bean TopicExchange testAckAndCallbackExchange() {return new TopicExchange("exchange_test_ack_callback");}

    @Bean Queue queue() { return new Queue("rpc_queue"); }
    @Bean Queue queueReply() { return new Queue("rpc_reply"); }
    @Bean Queue asyncQueue() { return new Queue("async_rpc_queue"); }
    @Bean Queue testAckAndCallbackQueue() { return new Queue("queue_test_ack_callback"); }

    @Bean Binding testAckAndCallbackBinding(TopicExchange testAckAndCallbackExchange,Queue testAckAndCallbackQueue) {return BindingBuilder.bind(testAckAndCallbackQueue).to(testAckAndCallbackExchange).with("*");}
}
