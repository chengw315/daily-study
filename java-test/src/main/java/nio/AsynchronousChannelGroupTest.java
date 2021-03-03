package nio;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/28
 **/
public class AsynchronousChannelGroupTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        fileAIOTest();
    }

    private static void socketAIOTest() throws IOException {
        AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withThreadPool(new ThreadPoolExecutor(4, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()));
        AsynchronousServerSocketChannel open = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
        open.bind(new InetSocketAddress(8800));
        //第一段阻塞accept，通过注册钩子事件，实现异步处理
        open.accept("accept", new CompletionHandler<AsynchronousSocketChannel, String>() {
            @Override
            public void completed(AsynchronousSocketChannel result, String attachment) {

                //第二段阻塞read+第三段阻塞拷贝数据到缓冲区，同样通过注册钩子事件，实现异步处理
                ByteBuffer allocate = ByteBuffer.allocate(1000);
                result.read(allocate, allocate, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        System.out.println(attachment);
                        System.out.println("read completed");
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {

                    }
                });
                System.out.println("accept completed");
            }

            @Override
            public void failed(Throwable exc, String attachment) {

            }
        });
    }

    private static void fileAIOTest() throws IOException, InterruptedException {
        //文件AIO
        File file = new File("1.txt");
        if (!file.exists()) {file.createNewFile();}
        Set<StandardOpenOption> read = new HashSet<>();
        read.add(StandardOpenOption.READ);
        AsynchronousFileChannel open = AsynchronousFileChannel.open(file.toPath(), read, new ThreadPoolExecutor(4, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()));
        ByteBuffer allocate = ByteBuffer.allocate(1000);
        //阻塞拷贝数据到缓冲区，通过注册钩子事件，异步处理
        open.read(allocate, allocate.position(), allocate, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println(attachment);
                System.out.println("completed");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(attachment);
                System.out.println("failed");
            }
        });
        Thread.sleep(10000);
    }
}
