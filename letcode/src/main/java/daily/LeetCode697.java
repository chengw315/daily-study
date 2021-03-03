package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/20
 **/
public class LeetCode697 {

    public static void main(String[] args) {
        int shortestSubArray1 = new LeetCode697().findShortestSubArray(new int[]{1});
        int shortestSubArray = new LeetCode697().findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2});
    }

    public int findShortestSubArray(int[] nums) {
        int[] counts = new int[50000];
        int[] firsts = new int[50000];
        for (int i = 0; i < firsts.length; i++) {
            firsts[i] = -1;
        }
        int[] lasts = new int[50000];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
            if (firsts[nums[i]] == -1) {
                firsts[nums[i]] = i;
            }
            lasts[nums[i]] = i;
        }
        int max = counts[0];
        for (int i = 1; i < counts.length; i++) {
            max = Math.max(counts[i],max);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < counts.length; i++) {
            if (max == counts[i]) {
                min = Math.min(lasts[i] - firsts[i] + 1, min);
            }
        }
        return min;
    }
}
