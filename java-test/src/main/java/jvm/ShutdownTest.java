package jvm;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/26
 **/
public class ShutdownTest {

    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0,20,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        });
        System.out.println("over");
    }

}
