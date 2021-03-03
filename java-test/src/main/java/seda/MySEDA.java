package seda;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description 我的seda实现，基于队列
 *
 * 物联网下发指令场景：接受请求->redis验证token->redis验证设备权限->下发指令->数据库记录指令下发结果 （五段socket阻塞）
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/11
 **/
public class MySEDA {
    Queue<Runnable> queue;

    public MySEDA() {
        queue = new LinkedList<>();
        queue.offer(() -> {

        });

    }

    public static void main(String[] args) throws IOException {
        //假设每个任务最长处理时间1.5s，最长延迟160 / 4 * 1.5 = 60s
        ThreadPoolExecutor accepterPool = new ThreadPoolExecutor(4, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(160));
        //NIO 单路复用 仅第一段阻塞（accept）
//        ServerSocket serverSocket = new ServerSocket();
//        serverSocket.bind(new InetSocketAddress("localhost",8080));
//        while (true) {
//            Socket accept = serverSocket.accept();
//            accepterPool.execute(()->{
//                try {
//                    InputStream inputStream = accept.getInputStream();
//                    //inputStream.read();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
        // 多路复用，只需要一个进程监听所有信号
//        Selector onlyOne = Selector.open();
//        SelectionKey acceptSemaphore = ServerSocketChannel.open()
//                .bind(new InetSocketAddress(8080))
//                .configureBlocking(false)
//                .register(onlyOne, SelectionKey.OP_ACCEPT);
//        while (true) {
//            acceptSemaphore.selector().select();
//
//        }
//        acceptSemaphore
    }
}
