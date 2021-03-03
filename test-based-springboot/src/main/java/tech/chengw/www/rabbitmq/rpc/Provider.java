//package tech.chengw.www.rabbitmq.rpc;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
///**
// * description RPC被调用方
// *
// * @author chengwj
// * @version 1.0
// * @date 2020/7/27
// **/
//@Component("rpcProvider")
//public class Provider {
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @RabbitListener(queues = "rpc_queue")
//    public void handle(String message, @Header(AmqpHeaders.REPLY_TO)String reply) {
//
//        amqpTemplate.convertAndSend(reply,message+"ack");
//    }
//
//}
