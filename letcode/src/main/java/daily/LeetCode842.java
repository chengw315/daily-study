package daily;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/8
 **/
public class LeetCode842 {

    public static void main(String[] args) {
        new LeetCode842().splitIntoFibonacci("121212121");
    }
    public List<Integer> splitIntoFibonacci(String S) {
        //区间动规 [left,right]区间是否是斐波那契数列
        //状态压缩 无
        boolean[][] dp = new boolean[S.length()][S.length()];
        HashSet<String> fbonacciSet = new HashSet<>();
        BigInteger a = new BigInteger("1"),b = new BigInteger("2");
        BigInteger max = new BigInteger(S);
        while (b.compareTo(max) <= 0) {
            fbonacciSet.add(a.toString());
            fbonacciSet.add(b.toString());
            BigInteger temp = b.add(a);
            a = b;
            b = temp;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j < dp.length; j++) {
//                dp[i][j] = dp[i][j-1] + dp[j-1][j]
            }
        }
        return null;
    }
}
