package _useful;

import java.util.Arrays;
import java.util.Stack;

/**
 * description 每日温度——对于每一天：比今天更高的气温，在今天之后的第几天
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/11
 **/
public class LeetCode739 {

    public static void main(String[] args) {
        boolean b1 = Arrays.equals(new LeetCode739().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        boolean b2 = Arrays.equals(new LeetCode739().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        boolean b11 = Arrays.equals(new LeetCode739().dailyTemperaturesWithStack(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        boolean b12 = Arrays.equals(new LeetCode739().dailyTemperaturesWithStack(new int[]{73, 74, 75, 71, 69, 72, 76, 73}), new int[]{1, 1, 4, 2, 1, 1, 0, 0});
    }

    /**
     * 单调栈（耗时战胜71.40%）
     * @param T
     * @return
     */
    public int[] dailyTemperaturesWithStack(int[] T) {

        int[] result = new int[T.length];
        for(int i = 0; i < T.length; i++) {
            result[i] = 0;
        }
        //递减栈
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < T.length; i++) {
            if(stack.empty()) {
                stack.push(i);
                continue;
            }
            if(T[i] <= T[stack.peek()]) {
                stack.push(i);
                continue;
            }
            while (T[i] > T[stack.peek()]) {
                Integer pop = stack.pop();
                result[pop] = i - pop;
                if(stack.empty()) {
                    stack.push(i);
                    continue;
                }
            }
            stack.push(i);
        }

        return result;
    }


    /**
     * 耗时战胜77.67%
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        //对应温度最近的一天的天数索引
        int[] days = new int[101];
        for(int i = 30; i < days.length; i++) {
            days[i] = Integer.MAX_VALUE;
        }

        for(int i = T.length-1; i >= 0; i--) {
            //更新温度对应天数
            days[T[i]] = i;
            T[i] = distanceOfNextHotterDay(days,T[i],i);
        }

        return T;
    }

    /**
     * 更热的一天还有几天
     * @param days 温度对应天数 数组
     * @param temp 当前温度
     * @param curDay 今天
     * @return
     */
    private int distanceOfNextHotterDay(int[] days,int temp,int curDay) {
        int firstDay = Integer.MAX_VALUE;
        for (int i = temp + 1; i < days.length; i++) {
            firstDay = Math.min(firstDay,days[i]);
        }
        if(firstDay > 30000) {
            return 0;
        }
        return firstDay - curDay;
    }
}
