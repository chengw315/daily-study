package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/18
 **/
public class LeetCode134 {

    public static void main(String[] args) {
        boolean b3 = new LeetCode134().canCompleteCircuit(new int[]{5, 0, 1}, new int[]{5, 1, 0}) == 2;
        boolean b = new LeetCode134().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}) == 3;
        boolean b1 = new LeetCode134().canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}) == -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //已知，均为非空数组、元素都是自然数
        int[] realCost = new int[gas.length];
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            realCost[i] = gas[i] - cost[i];
            sum += realCost[i];
        }
        if (sum < 0) {
            return -1;
        }

        //我知道环绕一周剩油sum，首先起点必是正数
        // 爆破？
        // 双指针
        int begin = 0, end = 0;
        int rest = 0;
        while (end < realCost.length) {
            rest += realCost[end];
            if (rest < 0) {
                begin = end + 1;
                rest = 0;
            }
            end++;
        }
        return begin;
    }
}
