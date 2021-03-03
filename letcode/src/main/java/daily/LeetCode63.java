package daily;

/**
 * description 机器人寻路
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/6
 **/
public class LeetCode63 {

    public static void main(String[] args) {
        //2
        int i = new LeetCode63().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        //0
        int i2 = new LeetCode63().uniquePathsWithObstacles(new int[][]{{1, 0}});
        //1
        int i3 = new LeetCode63().uniquePathsWithObstacles(new int[][]{{0}, {0}});
        //0
        int i4 = new LeetCode63().uniquePathsWithObstacles(new int[][]{{1}, {0}});
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dp = new int[obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            if(i==0) {
                dp[0] = 1 - obstacleGrid[0][0];
            } else {
                dp[0] = dp[0] == 0 || obstacleGrid[i][0] == 1 ? 0 : 1;
            }
            for(int j = 1; j < dp.length; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[dp.length-1];
    }
}
