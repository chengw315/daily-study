package daily;

/**
 * description 不用乘除、if、for、while、which 获取 1+2+...+n的和
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/2
 **/
public class LeetCode64 {

    public static void main(String[] args) {
        System.out.println(new LeetCode64().solve(64));
    }

    private int solve(int n) {
        int result = 0;
        //(1+n) * n / 2
        int nPlus1 = n + 1;
        return Math.multiplyExact(n, n + 1) >>> 1;
    }

    int sumNums(int n) {
        n &= (n += sumNums(n - 1));
        return n;
    }
}
