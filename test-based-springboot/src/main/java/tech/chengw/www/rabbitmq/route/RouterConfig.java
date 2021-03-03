package tech.chengw.www.rabbitmq.route;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *                                               |||||||||||||||||||||
 *                                               |queue_router_topic1|
 *  ||||||||       |||||||||||||||||||||||—— * —>|||||||||||||||||||||
 *  |生产者|——————>|exchange_router_topic|
 *  |||||||       |||||||||||||||||||||||— imei—>|||||||||||||||||||||
 *                                               |queue_router_topic2| 代码动态创建的临时队列与消费者
 *                                               |||||||||||||||||||||
 *
 *                                                 ||||||||||||||||||||||
 *                                                 |queue_router_direct1|
 *  ||||||||       ||||||||||||||||||||||||—imei1->||||||||||||||||||||||
 *  |生产者|——————>|exchange_router_direct|
 *  |||||||       ||||||||||||||||||||||||—imei2—>||||||||||||||||||||||
 *                                                |queue_router_direct2| 代码动态创建的临时队列与消费者
 *                                                ||||||||||||||||||||||
 *
 *                                                ||||||||||||||||||||||
 *                                                |queue_router_fanout1|
 *  ||||||||       ||||||||||||||||||||||||——————>||||||||||||||||||||||
 *  |生产者|——————>|exchange_router_fanout|
 *  |||||||       ||||||||||||||||||||||||———————>||||||||||||||||||||||
 *                                                |queue_router_fanout2| 代码动态创建的临时队列与消费者
 *                                                ||||||||||||||||||||||
 *
 *
 *
 *  ||||||||       |||||||||||                       ||||||||||||||||||||||
 *  |生产者|——————>| (null)  |—queue_router_default—>|queue_router_default|
 *  |||||||       |||||||||||                       ||||||||||||||||||||||
 *
 *
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/10
 **/
//@Configuration
public class RouterConfig {

    @Bean TopicExchange routerTopicExchange() { return new TopicExchange("exchange_router_topic");}
    @Bean Queue routerTopicQueue1() { return new Queue("queue_router_topic1");}
    @Bean Queue routerTopicQueue2() { return new Queue("queue_router_topic2");}
    @Bean Binding topicBinding1(TopicExchange routerTopicExchange,Queue routerTopicQueue1) { return BindingBuilder.bind(routerTopicQueue1).to(routerTopicExchange).with("*"); }

    @Bean DirectExchange routerDirectExchange() { return new DirectExchange("exchange_router_direct");}
    @Bean Queue routerDirectQueue1() { return new Queue("queue_router_direct1");}
    @Bean Queue routerDirectQueue2() { return new Queue("queue_router_direct2");}
    @Bean Binding directBinding1( DirectExchange routerDirectExchange,Queue routerDirectQueue1) { return BindingBuilder.bind(routerDirectQueue1).to(routerDirectExchange).with("imei1"); }

    @Bean FanoutExchange routerFanoutExchange() { return new FanoutExchange("exchange_router_fanout");}
    @Bean Queue routerFanoutQueue1() { return new Queue("queue_router_fanout1",true,false,true);}
    @Bean Queue routerFanoutQueue2() { return new Queue("queue_router_fanout2");}
    @Bean Binding fanoutBinding1( FanoutExchange routerFanoutExchange,Queue routerFanoutQueue1) { return BindingBuilder.bind(routerFanoutQueue1).to(routerFanoutExchange); }

    /**
     * 未绑定交换机的队列会自动绑定到 无名的DefaultExchange（属于DirectExchange），binding key与队列名一致
     */
    @Bean Queue routerDefaultQueue() { return new Queue("queue_router_default");}
}
