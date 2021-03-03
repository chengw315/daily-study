package threadpool;

import java.util.concurrent.*;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/11
 **/
public class ThreadPoolTest {
    static class MyTask implements Callable<Long> {
        public MyTask(long begin) {
            this.begin = begin;
        }

        long begin;
        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (long i = begin; i < 10000000000000L; i++) {
                sum += i;
            }
            return sum;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10000002, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Future<Long>[] futures = new Future[10000000];
        for (int i = 0; i < 10000000; i++) {
            futures[i] = (Future<Long>) executor.submit(new FutureTask<>(new MyTask(i)));
        }
        long sum = 0;
        for (int i = 0; i < 1000000000; i++) {
            sum += futures[i].get();
        }
        System.out.println(sum);
    }
}
