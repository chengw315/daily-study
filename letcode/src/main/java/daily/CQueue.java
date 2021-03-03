package daily;

import java.util.Stack;

/**
 * description 用两个栈实现队列
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/30
 **/
public class CQueue {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        int i = cQueue.deleteHead();
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        int i1 = cQueue.deleteHead();
        int i2 = cQueue.deleteHead();
    }

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public CQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void appendTail(int value) {
        stackIn.push(value);
    }

    public int deleteHead() {
        if(stackOut.empty()) {
            while (!stackIn.empty()) {
                stackOut.push(stackIn.pop());
            }
        }
        if (stackOut.empty()) {
            return -1;
        }
        return stackOut.pop();
    }
}
