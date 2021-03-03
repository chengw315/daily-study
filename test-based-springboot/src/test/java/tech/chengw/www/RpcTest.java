//package tech.chengw.www;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import tech.chengw.www.rabbitmq.rpc.Caller;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
///**
// * description
// *
// * @author chengwj
// * @version 1.0
// * @date 2020/7/27
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class RpcTest {
//    @Autowired
//    private Caller caller;
//    @Test
//    public void rpcTest() {
//        Future<Object> call = caller.call();
//        try {
//            Object o = call.get();
//            System.out.println(o);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
//}
