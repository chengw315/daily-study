package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/24
 **/
public class LeetCode459 {

    public static void main(String[] args) {
        boolean b4 = new LeetCode459().repeatedSubstringPattern("ababba");
        boolean b3 = new LeetCode459().repeatedSubstringPattern("bb");
        boolean b2 = new LeetCode459().repeatedSubstringPattern("aaaa");
        boolean b = new LeetCode459().repeatedSubstringPattern("abcabc");
        boolean b1 = new LeetCode459().repeatedSubstringPattern("aaaaaabb");
    }

    public boolean repeatedSubstringPattern(String s) {
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) num[s.charAt(i) - 'a']++;

        //获取公共因子
        List<Integer> factors = getFactorsExcept1(num);

        //使用每个公共因子测试
        for (int i = 0; i < factors.size(); i++) {
            if (repeatedSubstringPattern(s,s.length() / factors.get(i))) return true;
        }
        return false;
    }

    /**
     * 获取数组所有元素的公共因子（1除外）
     * @param num
     * @return
     */
    private List<Integer> getFactorsExcept1(int[] num) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            if(num[i] != 0) min = Math.min(min,num[i]);
        }
        ArrayList<Integer> result = new ArrayList<>();
        outer: for (int i = 2; i <= min; i++) {
            for (int j = 0; j < num.length; j++) {
                if (num[j] % i != 0) continue outer;
            }
            result.add(i);
        }
        return result;
    }

    private boolean repeatedSubstringPattern(String s, int subLength) {
        String substring = s.substring(0, subLength);
        for (int i = subLength; i < s.length();) {
            for (int j = 0; j < subLength; j++) {
                if (s.charAt(i++) != substring.charAt(j)) return false;
            }
        }
        return true;
    }

}
