package daily;

/**
 * description 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/9
 **/
public class LeetCode46 {

    public static void main(String[] args) {
        int num = new LeetCode46().translateNum(12258);
    }

    public int translateNum(int num) {
        String s = String.valueOf(num);
        int left = 1,right = 1;
        for (int i = 1; i < s.length(); i++) {
            int cur = right;
            if(s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && s.charAt(i) < '6')) {
                cur += left;
            }
            left = right;
            right = cur;
        }
        return right;
    }
}
