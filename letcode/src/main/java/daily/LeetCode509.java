package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/4
 **/
public class LeetCode509 {
    public static void main(String[] args) {
        int fib = new LeetCode509().new Solution().fib(4);
        boolean b = fib == 3;
    }
    class Solution {
        public int fib(int n) {
            if (n == 0) { return 0;}
            int first = 0;
            int second = 1;
            for (int i = 2; i < n; i++) {
                int temp = first;
                first = second;
                second = temp + first;
            }
            return first + second;
        }
    }
}
