package _useful;

/**
 * description 最长公共子串长度
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/1
 **/
public class LeetCode718 {

    public static void main(String[] args) {
        //3
        int length = new LeetCode718().findLengthDP(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
    }

    /**
     * 动归 + 状态压缩
     *  dp[i][j] 缓存 使用A[i]和B[j]做右边界的最大公共子串
     *
     * 那么dp[i+1][j+1] = dp[i][j] + A[i+1]==B[j+1]
     *
     * @param A
     * @param B
     * @return
     */
    public int findLengthDP(int[] A, int[] B) {
        int result = 0;
        int[] DP = new int[A.length];

        for(int i = 0; i < A.length; i++) {
            DP[i] = B[0] == A[i] ? 1 : 0;
            result = Math.max(result,DP[i]);
        }

        for(int i = 1; i < B.length; i++) {
            DP[0] = A[0] == B[i - 1] ? 1 : 0;
            result = Math.max(result,DP[0]);
            for(int j = A.length-1; j > 0; j--) {
                DP[j] = B[i] == A[j] ? DP[j - 1] + 1 : 0;
                result = Math.max(result,DP[j]);
            }
        }
        result = A[0] == B[B.length-1] ? Math.max(result,1) : result;
        return result;
    }

    /**
     * 暴力（超时）
     * @param A
     * @param B
     * @return
     */
    public int findLengthForce(int[] A, int[] B) {
        int result = 0;
        //起点
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                int length = 0;
                int indexA = i;
                int indexB = j;
                while (indexA < A.length && indexB < B.length && A[indexA++] == B[indexB++]) {
                    length++;
                }
                result = Math.max(length,result);
            }
        }

        return result;
    }
}
