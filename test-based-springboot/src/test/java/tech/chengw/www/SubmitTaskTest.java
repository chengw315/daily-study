package tech.chengw.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.chengw.www.task.TaskBody;
import tech.chengw.www.task.TaskSubmitter;

import java.util.HashMap;

/**
 * description 提交100个任务
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/23
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SubmitTaskTest {

    @Autowired
    private TaskSubmitter taskSubmitter;

    @Test
    public void submit100Task() {
        for (int i = 0; i < 100; i++) {
            HashMap<String, String> map = new HashMap<>(4);
            map.put("name","任务" + i);
            //提交任务
            submitTask(map,10 * 1000);
        }
    }

    /**
     * 提交任务
     * @param body
     * @param delayMillis
     * @return 任务标识（tag）
     */
    public long submitTask(Object body,long delayMillis) {
        return taskSubmitter.submit(new TaskBody().setBody(body),delayMillis);
    }

    /**
     * 删除任务
     * @param tag
     */
    public void deleteTask(long tag) {
       taskSubmitter.deleteTask(tag);
    }
}
