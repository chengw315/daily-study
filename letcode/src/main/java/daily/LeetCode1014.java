package daily;

/**
 * description 最佳观光组合
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/17
 **/
public class LeetCode1014 {

    public static void main(String[] args) {
        int i = new LeetCode1014().maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6});
        assert i == 11;
    }

    /**
     * 动态规划，A[i] + A[j] + i - j = A[i] + i + A[j] - j （i < j），
     *  对于 每个 j 而言使A[i]+i最大的i在遍历时就已经可以确定，无需再遍历i选取
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int maxPrefix = A[0];
        int max = Integer.MIN_VALUE;

        for(int i = 1; i< A.length; i++) {
            max = Math.max(max,maxPrefix + A[i] - i);
            maxPrefix = Math.max(maxPrefix,A[i] + i);
        }

        return max;
    }

    /**
     * 暴力穷举（超时）
     * @param A
     * @return
     */
    public int maxScoreSightseeingPairViolence(int[] A) {

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < A.length; i++) {
            for(int j = i + 1; j < A.length; j++) {
                max = Math.max(max, A[i] + A[j] + i - j);
            }
        }

        return max;
    }
}
