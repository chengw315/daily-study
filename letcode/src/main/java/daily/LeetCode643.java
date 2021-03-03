package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/4
 **/
public class LeetCode643 {
    public static void main(String[] args) {

    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i-k];
            max = Integer.max(max,sum);
        }
        return (double) max/k;
    }
}
