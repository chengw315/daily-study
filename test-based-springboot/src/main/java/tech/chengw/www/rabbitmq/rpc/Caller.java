//package tech.chengw.www.rabbitmq.rpc;
//
//import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.concurrent.Future;
//
///**
// * description RPC调用方
// *
// * @author chengwj
// * @version 1.0
// * @date 2020/7/27
// **/
//@Component
//public class Caller {
//
//    @Autowired
//    private AsyncRabbitTemplate asyncRabbitTemplate;
//
//    public Future<Object> call() {
//        HashMap<String, Object> map = new HashMap<>(4);
//        map.put("method","user.login");
//        map.put("args",new String[]{"cwj","chengwj"});
//        return asyncRabbitTemplate.convertSendAndReceive("rpc_queue",map);
//    }
//}
