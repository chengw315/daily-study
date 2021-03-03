package daily

fun main() {
    val solution = Solution64()
    //7
    val minPathSum = solution.minPathSum(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1)))
}

class Solution64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        if(grid.isEmpty()) return 0
        val dp = IntArray(grid[0].size)
        //第一排，左侧和
        dp[0] = grid[0][0]
        for (i in 1 until grid[0].size) {
            dp[i] = dp[i-1] + grid[0][i]
        }
        for(i in 1 until grid.size) {
            //第一列，上侧和
            dp[0] = dp[0] + grid[i][0]
            //其他列，取左、上小者
            for (j in 1 until grid[i].size) {
                dp[j] = Math.min(dp[j],dp[j-1]) + grid[i][j]
            }
        }
        return dp[dp.size-1]
    }
}