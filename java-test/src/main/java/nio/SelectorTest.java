package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * 多路复用的原理：首先确认几件事：
 *     1. Linux进程模型，所有进程-双向链表，待调度进程-红黑树（权重：虚拟运行时间），阻塞进程-队列集
 *     当进程执行到阻塞代码时，将进程从调度树中移到指定阻塞信号的队列中，信号到来时把队列下的所有进程移回调度树，轮到进程执行时再去获取信号量
 *     这个进程模型决定了一个进程一次只能监听一个信号
 *     2. BIO 一个进程阻塞监听一个信号，每个网络请求阻塞三次，需要的进程数多，这代表着：进程链表、调度树、阻塞进程队列的空间变大，会导致调度时间片减少、阻塞进程惊群现象
 *     3. 单路复用NIO，同一端口的accept阻塞信号是相同的，仅需一个进程去循环accept，等到信号后交给线程池处理即可
 *     4. 多路复用NIO，如何实现一个进程监听所有信号（accept信号、connect信号、每个socket的read、write信号）
 *     5. AIO
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/22
 **/
public class SelectorTest {

    /**
     * 简单处理一个accept信号（注册read信号）需要0.15ms，希望延迟在15ms以内，队列大小 1.5 / 0.15 * 4 = 400
     */
    private static ThreadPoolExecutor selectExecutor = new ThreadPoolExecutor(4,100,60, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(400));

    public static void main(String[] args) throws IOException {
        // socket通信通常由四种阻塞：1.等待连接accept 2.等待连接被接受connect 3.等待数据到来read 4.等待数据写入write
        //    服务端的流程是：等待accept——accept——等待read——read——处理数据——write——等待write完成
        //    客户端的流程是：connect——等待connect被接受——阻塞写write——等待read数据——read

        //注册accept事件
        Selector onlyOne = Selector.open();
        ServerSocketChannel.open()
                .bind(new InetSocketAddress("localhost",8800))
                .configureBlocking(false)
                //注册accept事件
                .register(onlyOne, SelectionKey.OP_ACCEPT);

        while(true) {
            //等待信号 (accept或是read)
            onlyOne.select(1000);
            //异步处理信号
            Collection<SelectionKey> selectionKeys = onlyOne.selectedKeys();
            selectionKeys.forEach(key -> selectExecutor.execute(new Task(key)));
        }
    }

    public static class Task implements Runnable {
        private SelectionKey key;
        @Override
        public void run() {
            System.out.println("keyOps=" + key.readyOps());
            //处理accept事件，accept并注册read事件
            if (key.isAcceptable()) {
                System.out.println("开始处理accept信号：");
                try {
                    long first = System.nanoTime();
                    SocketChannel accept = ((ServerSocketChannel) key.channel()).accept();
                    if (accept!=null) {
                        accept
                                .configureBlocking(false)
                                .register(key.selector(), SelectionKey.OP_READ);
                        long cost = System.nanoTime() - first;
                        System.out.println("accept信号处理（注册read事件）耗时" + cost + "ns");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //处理
            if (key.isReadable()) {
                try {
                    System.out.println("开始处理read信号：");
                    //处理数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    byte[] array = byteBuffer.array();
                    System.out.print("——数据：");
                    System.out.println(new String(array));
                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                    writeBuffer.put("go away".getBytes());
                    writeBuffer.flip();
                    channel.write(writeBuffer);
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public Task(SelectionKey selectionKey) {
            this.key = selectionKey;
        }
    }
}

