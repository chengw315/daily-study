package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/23
 **/
public class LeetCode1052 {
    public static void main(String[] args) {
        int i = new LeetCode1052().maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3);
    }
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int length = customers.length;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                sum += customers[i];
            }
        }
        int max = sum;
        for (int i = 1; i < length - X + 1; i++) {
            if (grumpy[i - 1] == 1) {
                sum -= customers[i - 1];
            }
            if (grumpy[i + X - 1] == 1) {
                sum += customers[i + X - 1];
            }
            max = Math.max(sum,max);
        }
        return max;
    }
}
