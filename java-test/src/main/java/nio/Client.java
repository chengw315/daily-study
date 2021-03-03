package nio;
import java.io.*;
import java.net.Socket;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/11
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8800);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("message\n");
        writer.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("数据——" + reader.readLine());
    }
}
