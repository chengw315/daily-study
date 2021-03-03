package _useful;

/**
 * description 戳气球（困难），每戳一个气球可获得 左邻近气球数值*右邻近气球数值*自身数值，然后左右邻近气球成为邻居
 *
 * 难点在于：
 *     1.戳破气球会生成新的数组，打乱原有次序（无法按照戳破气球的顺序进行分治）
 *     2.虽然 先戳1再戳2 和 先2后1 产生的数组一样，可以做缓存，但缓存的键值不好取，键值总不能为“去掉1、2”吧，这样的键值有n！个
 *
 * 收获：
 *     1.缓存可以分解，比如“去掉1、5”这样的缓存可以分解成【2~4】和【6~n】两块之和，区间dp可使用来分解缓存
 *     2.逆向思维，戳破气球会生成新状况，那如果只有一个气球呢？考虑使用最后戳破的气球而非最先戳破的气球 作为分治的界限
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/13
 **/
public class LeetCode312 {

    public static void main(String[] args) {
        int i = new LeetCode312().maxCoins(new int[]{6, 7, 8, 9, 10, 9, 8, 7, 6});
    }

    public int maxCoins(int[] nums) {
        int[] numCopy = new int[nums.length + 2];
        numCopy[0] = 1;
        numCopy[numCopy.length-1] = 1;
        for (int i = 0; i < nums.length;i++) {
            numCopy[i+1] = nums[i];
        }
        //dp[i][j]代表不戳爆i和j，i~j间戳气球可得硬币的最大值
        int[][] dp = new int[numCopy.length][numCopy.length];
        for (int i = 0; i < numCopy.length;i++) {
            for (int j = 0; j < numCopy.length; j++) {
                dp[i][j] = 0;
            }
        }
        //i倒序遍历
        for (int i = numCopy.length - 3; i >= 0;i--) {
            for (int j = i + 2; j < numCopy.length; j++) {
                for (int x = i + 1; x < j; x++) {
                    //为确保dp[x][j]先于dp[i][j]被求值，令i倒序遍历
                    //令最后一个被戳爆的是x，那么dp[i][j] = max |dp[i][x]+dp[x][j]+ ni*nj*nx|
                    //最后一个被戳爆的是x，则最后一次获得硬币为 nx*左边界（ni）*有边界（nj）
                    dp[i][j] = Math.max(dp[i][j],dp[i][x] + dp[x][j] + numCopy[x] * numCopy[i] * numCopy[j]);
                }
            }
        }
        return dp[0][numCopy.length-1];
    }

}
