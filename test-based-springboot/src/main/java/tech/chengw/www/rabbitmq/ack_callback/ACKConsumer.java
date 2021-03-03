package tech.chengw.www.rabbitmq.ack_callback;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import tech.chengw.www.MainApplication;

import java.io.IOException;

/**
 * description 手动ACK的消费者
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/12
 **/
@Component
public class ACKConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        logger.info("ACKConsumer消费消息：{}", message);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume1(String qwewqas1, String message3, Channel channel1, String message, Channel channel, long tag3, @Header(AmqpHeaders.DELIVERY_TAG) long tag, long tag2) throws IOException, InterruptedException {
        logger.info("ACKConsumer consume1消费消息：{}", message);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        logger.info("ACKConsumer consume2消费消息：{}", tag);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume3(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        logger.info("ACKConsumer consume3消费消息：{}", tag);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume4(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        logger.info("ACKConsumer consume4消费消息：{}", tag);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }

    @RabbitListener(queues = "queue_test_ack_callback")
    public void consume5(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException, InterruptedException {
        logger.info("ACKConsumer consume5消费消息：{}", tag);
        channel.basicAck(tag, false);
//        channel.basicNack(tag,false,true);
    }
}
