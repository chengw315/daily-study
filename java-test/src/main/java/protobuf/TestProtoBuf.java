package protobuf;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/10
 **/
public class TestProtoBuf {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        String taskName = "任务11";
        byte[] nameBytes = taskName.getBytes();
        TaskProto.Task task = TaskProto.Task.newBuilder()
                .setId(1)
                .setName(taskName)
                .build();

        CodedOutputStream.computeInt32Size(1,128);
        byte[] bytes = task.toByteArray();
        TaskProto.Task.parseFrom(bytes);
    }
}
