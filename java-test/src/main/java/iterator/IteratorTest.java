package iterator;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/11
 **/
public class IteratorTest {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> objects = new LinkedBlockingQueue();
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        int[] count = new int[1];
        objects.forEach(o -> {objects.poll();objects.poll(); count[0]++;});
//        objects.stream().forEach(o -> {objects.poll();});
    }
}
