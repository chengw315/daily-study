package tech.chengw.www.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/31
 **/
@Service
public class RedisMassInsert {

//    @Value("#{redisMassInsert.host}")
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;

    public void massInsert() throws IOException {
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        StringBuilder builder = new StringBuilder();
        builder.append("*2\r\n$4\r\nauth\r\n$6\r\nchengw\r\n");
        for (int i = 0; i < 1000000; i++) {
            builder.append("*3\r\n")
                    .append("$3\r\nset\r\n")
                    .append("$10\r\nkey")
                    .append(1000000 + i)
                    .append("\r\n")
                    .append("$7\r\n")
                    .append(1000000 + i)
                    .append("\r\n");
        }
        long cur = System.currentTimeMillis();
        outputStream.write(builder.toString().getBytes());
        outputStream.flush();
        byte[] bytes = new byte[8192];
        socket.getInputStream().read(bytes);
        System.out.println("cost:" + ((System.currentTimeMillis() - cur) / 1000) + "s");
        System.out.println(new String(bytes));
    }

    public static void main(String[] args) throws IOException {
        new RedisMassInsert().massInsert();
    }
}
