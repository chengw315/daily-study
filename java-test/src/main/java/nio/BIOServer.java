package nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/11
 **/
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8800);
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("已连接");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String s = bufferedReader.readLine();
            System.out.println("已接受消息：" + s);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            bufferedWriter.write("message");
            bufferedWriter.flush();
            System.out.println("已回复");
            accept.close();
            System.out.println("已关闭连接");
        }
    }
}
