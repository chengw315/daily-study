package serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/26
 **/
public class SerializableTest implements Serializable {

    private int data1;
    private String data2;
    private Date data3;

    public SerializableTest(int data1, String data2, Date data3) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("serial.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(new SerializableTest(1,"2",new Date()));
    }
}
