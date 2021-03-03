package tech.chengw.www.delay_task;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.chengw.www.annocation.TaskExecutor;
import tech.chengw.www.task.Acker;
import tech.chengw.www.task.TaskBody;

/**
 * description 注册任务执行者的实例
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/23
 **/
@Component
public class Executor {

    private Logger logger = LoggerFactory.getLogger(Executor.class);

    /**
     * 任务执行器1
     * @param tag
     * @param taskBody
     * @param acker
     */
    @TaskExecutor(autoAck = false)
    public void executeTask1(long tag, TaskBody taskBody, Acker acker) {
        logger.info("任务执行器1消费到任务-{}，进行ack", JSON.toJSONString(taskBody));
        acker.ack(tag);
    }

    /**
     * 任务执行器2
     * @param body
     */
    @TaskExecutor
    public void executeTask2(TaskBody body) {
        logger.info("任务执行器2消费到任务-{}，自动ack", JSON.toJSONString(body));
    }

    /**
     * 任务执行器3
     * @param tag
     * @param acker
     */
    @TaskExecutor(autoAck = false)
    public void executeTask3(long tag, Acker acker) {
        logger.info("任务执行器3消费到任务，nack重新入列，延迟20s");
        acker.nack(tag,true,20 * 1000);
    }

    /**
     * 任务执行器4
     * @param body
     */
    @TaskExecutor(autoAck = true)
    public void executeTask4(TaskBody body) {
        logger.info("任务执行器4消费到任务-{}，未ack，将超时重新执行，默认延迟60s", JSON.toJSONString(body));
    }
}
