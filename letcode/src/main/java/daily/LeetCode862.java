package daily;

/**
 * description todo 和>=K的 最短子数组 的长度（困难）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/6
 **/
public class LeetCode862 {

    public static void main(String[] args) {
        boolean b = new LeetCode862().shortestSubarray(new int[]{-28, 81, -20, 28, -29}, 89) == 3;
        boolean b1 = new LeetCode862().shortestSubarray(new int[]{17,85,93,-45,-21}, 150) == 2;
    }

    public int shortestSubarray(int[] A, int K) {
        int minPositive = Integer.MAX_VALUE;
        boolean isLong = A.length > 1000000;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0 && A[i] < minPositive) {
                minPositive = A[i];
            }
        }
        int count = 15;
        int min = A.length + 1;
        for (int left = 0; left < A.length; left++) {
            if (A[left] <= 0) {
                continue;
            }
            int sum = 0;
            for (int right = left; right < A.length; right++) {
                sum += A[right];
                if(sum >= K) {
                    for (; left < right; left++) {
                        sum -= A[left];
                        if (sum < K) {
                            if (isLong && --count<0) {
                                return Math.min(min,right-left+1);
                            }
                            break;
                        }
                    }
                    min = Math.min(min,right-left+1);
                    break;
                }
                if (sum <= 0 || (isLong && sum <= 10)) {
                    left = right;
                    break;
                }
            }
        }
        if(min > A.length) {
            return -1;
        }
        return min;
    }
}
