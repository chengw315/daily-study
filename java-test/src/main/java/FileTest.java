import java.io.FileWriter;
import java.io.IOException;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/7
 **/
public class FileTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(()->{
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("E://1.txt",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                try {
                    fileWriter.write("线程1写入" + i);
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).run();
        FileWriter fileWriter = new FileWriter("E://1.txt",true);
        for (int i = 0; i < 10; i++) {
            fileWriter.write("主线程写入" + i);
            Thread.sleep(1100);
        }
        fileWriter.flush();
    }
}
