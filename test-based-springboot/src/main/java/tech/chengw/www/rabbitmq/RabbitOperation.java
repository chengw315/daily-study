package tech.chengw.www.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * description
 *  1. 代码登录RabbitMQ服务器
 *  2. 代码短期消费RabbitMq服务器队列
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/11
 **/
public class RabbitOperation {

    public static void main(String[] args) throws InterruptedException {
        //配置连接
        String host = "MyIp";
        int port = 5672;
        String username = "git_test";
        String password = "chengw";
        //只是单次登录RabbitMq服务器，无需维护连接，使用最简单的ConnectionFactory就行（没找到最简单的）
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(new URI("amqp://"+username+":"+password+"@"+host+":" + port));
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("git_test");

//        loginAndCreateExchangeByRabbitAdmin(connectionFactory);
//        loginAndCreateExchangeByChannel(connectionFactory);
        createTransientConsumer(connectionFactory);
    }

    /**
     * 短期消费者 demo，通过 {@link AbstractMessageListenerContainer#start()}和{@link AbstractMessageListenerContainer#stop()}实现
     * @param connectionFactory
     * @throws InterruptedException
     */
    private static void createTransientConsumer(CachingConnectionFactory connectionFactory) throws InterruptedException {
        //声明队列
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        String queueName = "queue_transient_listened";
        rabbitAdmin.declareQueue(new Queue(queueName,false));

        //发信
        Thread thread = new Thread(() -> {
            while (true) {
                rabbitAdmin.getRabbitTemplate().convertAndSend(queueName, "");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();


        //代码声明消费者
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.setQueueNames(queueName);
        listenerContainer.setMessageListener(message -> System.out.println(message));
        listenerContainer.start();
        System.out.println("start consumer");
        Thread.sleep(5000);

        //代码停止消费者
        listenerContainer.stop();
        System.out.println("stop consumer");
        Thread.sleep(3000);
        System.out.println("over");

        //释放资源
        thread.interrupt();
        connectionFactory.destroy();
    }

    /**
     * 使用RabbitAdmin方式登录并创建交换机
     *
     * @param connectionFactory
     */
    private static void loginAndCreateExchangeByRabbitAdmin(CachingConnectionFactory connectionFactory) {
        //通过 rabbitAdmin 创建连接并创建交换机
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        try {
            rabbitAdmin.deleteExchange("exchange_test_cwj");
            rabbitAdmin.declareExchange(new FanoutExchange("exchange_test_cwj"));
            rabbitAdmin.declareExchange(new FanoutExchange("exchange_test_cwj"));
        } catch (Exception e) {
            e.printStackTrace();
            //失败：登录失败、校权失败
            String message = e.getMessage();
        }
        //释放资源
        connectionFactory.destroy();
    }

    /**
     * 使用channel方式登录并创建交换机
     * @param connectionFactory
     */
    private static void loginAndCreateExchangeByChannel(CachingConnectionFactory connectionFactory) throws IOException, TimeoutException {
        //创建连接并通过channel创建交换机，只是创建交换机，无需事务
        Channel channel = connectionFactory.createConnection().createChannel(false);
        AMQP.Exchange.DeclareOk declareOk1;
        AMQP.Exchange.DeclareOk declareOk2;
        channel.exchangeDelete("exchange_test_cwj");
        try {
            declareOk1 = channel.exchangeDeclare("exchange_test_cwj", "fanout");
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            declareOk2 = channel.exchangeDeclare("exchange_test_cwj", "fanout");
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //释放资源
        connectionFactory.destroy();
    }
}
