package tech.chengw;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/28
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 120,time = 1,timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10000,time = 1,timeUnit = TimeUnit.MICROSECONDS)
@Threads(8)
@Fork(1)
@State(Scope.Benchmark)
public class JMHTest {

    public static void main(String[] args) throws RunnerException {
        testNTime(10000);
    }

    private static void testNTime(int num) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHTest.class.getSimpleName())
                .measurementIterations(num)
                .output("/root/testRecord.log")
                .build();
        new Runner(options).run();
    }


    /**
     * System.currentMillisTime测试
     * @return 将结果返回是为了防止死码消除（编译器将 无引用的变量 当成无用代码优化掉）
     */
    @Benchmark
    public long testSystem() {
        return System.currentTimeMillis();
    }

    /**
     * 缓存时钟测试
     * @return
     */
    @Benchmark
    public long testCacheClock() {
        return JMHTest.CacheClock.currentTimeMillis();
    }

    /**
     * 缓存时钟，缓存System.currentTimeMillis()的值，每隔1s更新一次
     */
    public static class CacheClock{
        private static ScheduledExecutorService timer = new ScheduledThreadPoolExecutor(1);
        private static volatile long timeMillis;
        static {
            timer.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    timeMillis = System.currentTimeMillis();
                }
            },0,1000,TimeUnit.MILLISECONDS);
        }
        public static long currentTimeMillis() {
            return timeMillis;
        }
    }
}
