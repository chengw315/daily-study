package tech.chengw.www.rabbitmq.ack_callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description 配置生产者 发信失败的Hook，为什么不在发信的时候挂钩子？这样多个生产者重用rabbitTemplate就不爽了
 *  1. 前提，配置文件开启生产者发信失败回调
 *  2. 创建Hook，挂到RabbitTemplate上
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/12
 **/
@Component
public class ProviderCallBackConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.error("发信完成，关联数据-{}，ack-{}，原因-{}",correlationData,ack,cause);
        /**————————处理————————*/
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error("发信失败，失败码-{}，失败信息-{}，交换机-{}，路由键-{}",replyCode,replyText,exchange,routingKey);
        /**————————处理————————*/
    }

    @PostConstruct
    void config() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}
